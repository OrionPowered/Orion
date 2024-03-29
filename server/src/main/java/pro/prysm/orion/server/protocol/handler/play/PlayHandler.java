package pro.prysm.orion.server.protocol.handler.play;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.text.Component;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.entity.player.*;
import pro.prysm.orion.api.event.event.IncomingPluginMessageEvent;
import pro.prysm.orion.api.event.event.PlayerJoinEvent;
import pro.prysm.orion.api.message.Message;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.PlayerInfoAction;
import pro.prysm.orion.common.protocol.incoming.play.*;
import pro.prysm.orion.common.protocol.incoming.play.ClientSettings;
import pro.prysm.orion.common.protocol.outgoing.play.*;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.Server;
import pro.prysm.orion.server.entity.player.ImplPlayer;
import pro.prysm.orion.server.protocol.bidirectional.PlayerAbilities;
import pro.prysm.orion.server.protocol.handler.AbstractHandler;
import pro.prysm.orion.server.protocol.incoming.*;
import pro.prysm.orion.server.protocol.incoming.EntityAction;
import pro.prysm.orion.server.protocol.outgoing.JoinGame;
import pro.prysm.orion.server.world.LevelProvider;
import pro.prysm.orion.server.world.World;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

public class PlayHandler extends AbstractHandler {
    private final ImplPlayer player;
    private final Movement movement;
    private int viewDistance;
    private int teleportId; // TODO: Implement checking of teleport ids
    private long lastPingTime;
    private long keepAliveId;

    public PlayHandler(ImplPlayer player) {
        super(player.getConnection());
        this.player = player;
        movement = new Movement(player);
        joinGame();
    }

    public void updateViewDistance(int distance) {
        this.viewDistance = Math.min(distance, Orion.getServer().getViewDistance());
    }

    private void joinGame() {
        Server server = Orion.getServer();
        LevelProvider level = server.getLevelProvider();
        World world = level.getWorldForPlayer(player.uuid());
        player.setWorld(world);

        player.setGameMode(GameMode.CREATIVE);                 // TODO: Implement Gamemode

        JoinGame joinGame = new JoinGame();
        joinGame.setEntityId(player.getEntityId());
        joinGame.setHashedSeed(world.getSeedHash());
        joinGame.setGamemode(player.getGameMode());
        joinGame.setPreviousGamemode(player.getGameMode());
        joinGame.setDimension(level.getDimensionProvider().getDimensionType(world.getDimension()));
        joinGame.setWorldName(world.getName());
        joinGame.setHardcore(world.isHardcore());

        if (!world.isVoid()) {
            if (!world.hasSavedPlayerData(player.getProfile().getUniqueId()))
                player.savePlayerData(world); // new player
            Optional<CompoundBinaryTag> playerData = world.getPlayerData(player.getProfile().getUniqueId());
            player.readPlayerData(playerData.orElseThrow());
        }

        connection.sendPacket(joinGame);
        connection.sendPacket(new PluginMessageOut("minecraft:brand", Orion.getProtocol().getSlp().getVersion().getName().getBytes(StandardCharsets.UTF_8)));
    }

    private void finalizeJoin() {
        Server server = Orion.getServer();
        PlayerJoinEvent playerJoinEvent = new PlayerJoinEvent(player);
        Orion.getEventBus().post(playerJoinEvent);
        if (playerJoinEvent.isCancelled())
            player.getConnection().disconnect(Component.text("Kicked from server."));   // TODO: Change
        else {
            server.addPlayer(player);

            Orion.getLogger().info("{} ({}) has logged in", player.getProfile().getUsername(), connection.getAddress());
            Orion.getLogger().debug("Player {} has logged in at {}", player.getProfile().getUsername(), player.getLocation());

            if (!player.getWorld().isVoid()) sendChunks();
            else player.setLocation(player.getWorld().getSpawn());

            Orion.getScheduler().schedule(() -> { // This is simply to give the client time to receive some chunks
                connection.sendPacket(new PlayerPositionAndLook(player.getLocation()));
                player.setStatus(PlayerStatus.ACTIVE); // Client is fully logged in now
            }, 20L);

            // Testing
            connection.sendPacket(new PlayerlistHeaderFooter(
                    new Message("<color:#2fc1fa>Orion Server Software</color>").toComponent(),
                    new Message("<color:#2fc1fa>Orion Server Software</color>").toComponent()
            ));
        }
    }

    public void sendKeepAlive() {
        KeepAliveOut keepAlive = new KeepAliveOut();
        connection.sendPacket(keepAlive);
        keepAliveId = keepAlive.getKeepAliveId();
        Orion.getLogger().debug("Sent keepalive to {} ({})", connection.getAddress(), player.getProfile().getUsername());
    }

    public void sendPing() {
        Ping ping = new Ping();
        lastPingTime = ping.getTime();
        connection.sendPacket(ping);
    }

    public void sendChunks() {
        sendChunks(player.getLocation());
    }

    public void sendChunks(Location center) {
        int centerX = center.getChunkX();
        int centerZ = center.getChunkZ();

        // this has an execution time of O(n^2) which is not preferred.
        // TODO: Rewrite chunk sending
        for (int x = centerX - viewDistance; x <= centerX; x++) {
            for (int z = centerZ - viewDistance; z <= centerZ; z++) {
                if ((x - centerX) * (x - centerX) + (z - centerZ) * (z - centerZ) <= viewDistance * viewDistance) {
                    int chunkX = centerX - (x - centerX);
                    int chunkZ = centerZ - (z - centerZ);
                    player.sendChunkAsync(x, z);
                    player.sendChunkAsync(x, chunkZ);
                    player.sendChunkAsync(chunkX, z);
                    player.sendChunkAsync(chunkX, chunkZ);
                }
            }
        }
    }

    @Override
    public void onDisconnect() {
        Orion.getServer().removePlayer(player);
        connection.sendPacket(new PlayerInfo(PlayerInfoAction.REMOVE_PLAYER, List.of(player)));
        Orion.getLogger().info("{} ({}) has logged out", player.getProfile().getUsername(), player.getConnection().getAddress());
    }

    @Override
    public void handle(ClientSettings packet) {
        player.setSettings(new pro.prysm.orion.api.entity.player.ClientSettings(
                packet.getLocale(),
                packet.getViewDistance(),
                (packet.getChatMode() == 0) ? ChatMode.ENABLED : (packet.getChatMode() == 1 ? ChatMode.COMMANDS_ONLY : ChatMode.HIDDEN),
                packet.isColoredChat(),
                packet.getSkinParts(),
                (packet.getMainHand() == 0) ? Hand.LEFT : Hand.RIGHT
        ));
        updateViewDistance(packet.getViewDistance());

        // Once we know the player's settings, we can let them join.
        finalizeJoin();
    }

    @Override
    public void handle(KeepAliveIn packet) {
        if (keepAliveId != packet.getKeepAliveId()) connection.disconnect(Component.text("Invalid Keep Alive ID"));
        else
            player.setLatency((int) (keepAliveId - packet.getKeepAliveId())); // We can calculate latency from keep alive
    }

    @Override
    public void handle(Pong packet) {
        if (packet.getTime() != lastPingTime) connection.disconnect(Component.text("Invalid Pong ID"));
        else if (lastPingTime != 0) player.setLatency((int) (System.currentTimeMillis() - lastPingTime));
    }

    @Override
    public void handle(PluginMessageIn packet) {
        if (packet.getChannel().equals("minecraft:brand"))
            player.setBrand(new String(packet.getData(), StandardCharsets.UTF_8));
        else
            Orion.getEventBus().post(new IncomingPluginMessageEvent(packet.getChannel(), new PacketByteBuf(packet.getData())));
    }

    public void handle(PlayerMovement packet) {
        if (player.getStatus() == PlayerStatus.CONNECTING) return;
        player.getLocation().setOnGround(packet.isOnGround());
    }

    @Override
    public void handle(PlayerPosition packet) {
        if (player.getStatus() == PlayerStatus.CONNECTING) return;
        Location to = player.getLocation().clone();
        to.setX(packet.getX());
        to.setY(packet.getY());
        to.setZ(packet.getZ());
        to.setOnGround(packet.isOnGround());

        player.setLocation(movement.playerMove(to, player.getLocation()));
    }

    @Override
    public void handle(PlayerRotation packet) {
        if (player.getStatus() == PlayerStatus.CONNECTING) return;
        Location to = player.getLocation().clone();
        to.setYaw(packet.getYaw());
        to.setPitch(packet.getPitch());
        to.setOnGround(packet.isOnGround());
        player.setLocation(movement.playerMove(to, player.getLocation()));
    }

    @Override
    public void handle(PlayerPositionAndRotation packet) {
        if (player.getStatus() == PlayerStatus.CONNECTING) return;
        Location to = player.getLocation().clone();
        to.setX(packet.getX());
        to.setY(packet.getY());
        to.setZ(packet.getZ());
        to.setYaw(packet.getYaw());
        to.setPitch(packet.getPitch());
        to.setOnGround(packet.isOnGround());

        player.setLocation(movement.playerMove(to, player.getLocation()));
    }

    @Override
    public void handle(TeleportConfirm packet) {
    }

    @Override
    public void handle(ChatMessageIn packet) {
        Server server = Orion.getServer();
        Player sender = server.getPlayer(connection).orElseThrow();
        Component message = packet.getMessage();
        // TODO: post chat event
        server.broadcast(sender, message);
    }

    @Override
    public void handle(EntityAction packet) {
        switch(packet.getAction()) {
            case START_SNEAKING -> player.setSneaking(true);
            case STOP_SNEAKING -> player.setSneaking(false);
            case START_SPRINTING -> player.setSprinting(true);
            case STOP_SPRINTING -> player.setSprinting(false);
            default -> Orion.getLogger().warn("Unimplemented EntityAction {}", packet.getAction());
        }
    }

    @Override
    public void handle(PlayerAbilities.Incoming packet) {
        switch(packet.getAbility()) {
            case FLYING -> player.setFlying(true);
            case NONE -> player.setFlying(false);
            default -> Orion.getLogger().warn("Unimplemented PlayerAbility: {}", packet.getAbility());
        }
    }
}