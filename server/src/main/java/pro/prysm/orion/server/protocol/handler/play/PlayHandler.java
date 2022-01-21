package pro.prysm.orion.server.protocol.handler.play;

import com.alexsobiek.anvil.Level;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.text.Component;
import pro.prysm.orion.api.data.ChatMode;
import pro.prysm.orion.api.data.GameMode;
import pro.prysm.orion.api.data.Hand;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.entity.Player;
import pro.prysm.orion.api.event.event.IncomingPluginMessageEvent;
import pro.prysm.orion.api.event.event.PlayerJoinEvent;
import pro.prysm.orion.api.message.Message;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.Server;
import pro.prysm.orion.server.entity.player.ImplPlayer;
import pro.prysm.orion.server.protocol.Protocol;
import pro.prysm.orion.server.protocol.handler.ProtocolHandler;
import pro.prysm.orion.server.protocol.incoming.play.*;
import pro.prysm.orion.server.protocol.outgoing.play.*;
import pro.prysm.orion.server.scheduler.OrionScheduler;
import pro.prysm.orion.server.world.LevelManager;
import pro.prysm.orion.server.world.dimension.DimensionProvider;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

public class PlayHandler extends ProtocolHandler {
    private final ImplPlayer player;
    private final Movement movement;
    private final int teleportId = 0; // TODO: Implement checking of teleport ids
    private UUID keepAliveTask;
    private UUID chunkTask;
    private long keepAliveId;

    public PlayHandler(ImplPlayer player) {
        super(player.getConnection());
        this.player = player;
        movement = new Movement(player);
        joinGame();
    }

    private void joinGame() {
        Server server = Orion.getServer();
        Protocol protocol = server.getProtocol();
        LevelManager levelManager = server.getLevelManager();
        JoinGame joinGame = protocol.getJoinGamePacket();
        connection.sendPacket(new PluginMessageOut("minecraft:brand", protocol.getSlpData().getVersion().getName().getBytes(StandardCharsets.UTF_8)));
        joinGame.setEntityId(player.getEntityId());

        if (!levelManager.isVoidWorld()) {
            DimensionProvider dimension = levelManager.getDimension();
            Level level = levelManager.getLevel();

            if (!level.hasSavedPlayerData(player.getProfile().getUniqueId())) {
                player.setLocation(new Location(level.getSpawnX(), level.getSpawnY(), level.getSpawnZ(), 0F, 90F, false)); // TODO: This is a temp solution
                player.savePlayerData(level);
            }

            Optional<CompoundBinaryTag> playerData = level.getPlayerData(player.getProfile().getUniqueId());

            player.readPlayerData(playerData.orElseThrow());
            player.setGameMode(GameMode.SPECTATOR);
            joinGame.setGamemode(player.getGameMode());             // TODO: Implement Gamemode
            joinGame.setPreviousGamemode(player.getGameMode());
            joinGame.setWorldName(level.getName());                // TODO: Implement worlds
        } else {
            player.setLocation(new Location(0, 64, 0, 0, 0, true));
        }

        connection.sendPacket(joinGame);

    }

    private void finalizeJoin() {
        Server server = Orion.getServer();
        PlayerJoinEvent playerJoinEvent = new PlayerJoinEvent(player);
        Orion.getEventBus().post(playerJoinEvent);
        if (playerJoinEvent.isCancelled()) {
            player.getConnection().disconnect(Component.text("Kicked from server."));   // TODO: Change
            return;
        }

        server.addPlayer(player);
        connection.sendPacket(new PlayerPositionAndLook(player.getLocation()));

        startKeepAlive();
        Orion.getLogger().info("{} ({}) has logged in", player.getProfile().getUsername(), connection.getAddress());
        Orion.getLogger().debug("Player {} has logged in at {}", player.getProfile().getUsername(), player.getLocation());
        if (!server.getLevelManager().isVoidWorld()) startChunkSending();

        // Testing
        connection.sendPacket(new PlayerlistHeaderFooter(
                new Message("<color:#2fc1fa>Orion Server Software</color>").toComponent(),
                new Message("<color:#2fc1fa>Orion Server Software</color>").toComponent()
        ));
    }

    private void startKeepAlive() {
        keepAliveTask = Orion.getScheduler().scheduleAtFixedRate(() -> {
            if (!connection.isActive())
                return;

            KeepAliveOut keepAlive = new KeepAliveOut();
            connection.sendPacket(keepAlive);
            keepAliveId = keepAlive.getKeepAliveId();
            Orion.getLogger().debug("Sent keepalive to {} ({})", connection.getAddress(), player.getProfile().getUsername());
        }, 10L, (25 * OrionScheduler.TPS)); // Every 25 seconds (30 seconds resulted in random timeouts)
    }

    public void startChunkSending() {
        chunkTask = Orion.getScheduler().scheduleAtFixedRate(() -> {
            LevelManager levelManager = Orion.getServer().getLevelManager();
            Location loc = player.getLocation();

            int baseX = (int) loc.getX() >> 4;
            int baseZ = (int) loc.getZ() >> 4;
            int halfDistance = player.getSettings().getViewDistance() / 2;

            int minX = baseX - halfDistance;
            int minZ = baseZ - halfDistance;
            int maxX = baseX + halfDistance;
            int maxZ = baseZ + halfDistance;

            for (int x = minX; x <= maxX; x++) {
                for (int z = minZ; z < maxZ; z++) {
                    player.sendChunkAsync(levelManager, x, z);
                }
            }
        }, 2L, 60L);
    }

    @Override
    public void onDisconnect() {
        Orion.getServer().removePlayer(player);
        Orion.getScheduler().cancel(chunkTask);
        Orion.getScheduler().cancel(keepAliveTask);
    }

    @Override
    public void handle(ClientSettings packet) {
        player.setSettings(new pro.prysm.orion.api.data.ClientSettings(
                packet.getLocale(),
                packet.getViewDistance(),
                (packet.getChatMode() == 0) ? ChatMode.ENABLED : (packet.getChatMode() == 1 ? ChatMode.COMMANDS_ONLY : ChatMode.HIDDEN),
                packet.isColoredChat(),
                packet.getSkinParts(),
                (packet.getMainHand() == 0) ? Hand.LEFT : Hand.RIGHT
        ));

        // Once we know the player's settings, we can let them join.
        finalizeJoin();
    }

    @Override
    public void handle(KeepAliveIn packet) {
        if (keepAliveId != packet.getKeepAliveId()) connection.disconnect(Component.text("Invalid Keep Alive ID"));
    }

    @Override
    public void handle(PluginMessageIn packet) {
        if (packet.getChannel().equals("minecraft:brand"))
            player.setBrand(new String(packet.getData(), StandardCharsets.UTF_8));
        else Orion.getEventBus().post(new IncomingPluginMessageEvent(packet.getChannel(), packet.getData()));
    }

    @Override
    public void handle(PlayerPosition packet) {
        Location to = player.getLocation().clone();
        to.setX(packet.getX());
        to.setY(packet.getY());
        to.setZ(packet.getZ());
        to.setOnGround(packet.isOnGround());

        player.setLocation(movement.playerMove(to, player.getLocation()));
    }

    @Override
    public void handle(PlayerRotation packet) {
        Location to = player.getLocation().clone();
        to.setYaw(packet.getYaw());
        to.setPitch(packet.getPitch());
        to.setOnGround(packet.isOnGround());
        player.setLocation(movement.playerMove(to, player.getLocation()));
    }

    @Override
    public void handle(PlayerPositionAndRotation packet) {
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
        Player sender = server.getPlayer(packet.getConnection());
        Component message = packet.getMessage();
        // TODO: post chat event
        server.broadcast(sender, message);
    }
}
