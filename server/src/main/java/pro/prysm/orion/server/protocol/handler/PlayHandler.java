package pro.prysm.orion.server.protocol.handler;

import com.alexsobiek.anvil.Level;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import pro.prysm.orion.api.data.ChatMode;
import pro.prysm.orion.api.data.GameMode;
import pro.prysm.orion.api.data.Hand;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.event.event.PlayerMoveEvent;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.data.Dimension;
import pro.prysm.orion.server.entity.player.ImplPlayer;
import pro.prysm.orion.server.protocol.incoming.play.*;
import pro.prysm.orion.server.protocol.outgoing.play.*;
import pro.prysm.orion.server.scheduler.OrionScheduler;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

public class PlayHandler extends ProtocolHandler {
    private final ImplPlayer player;
    private final int teleportId = 0; // TODO: Implement checking of teleport ids
    private UUID keepAliveTask;
    private long keepAliveId;

    public PlayHandler(ImplPlayer player) {
        super(player.getConnection());
        this.player = player;
        joinGame();
    }

    private void joinGame() {
        Level level = connection.getProtocol().getLevelManager().getLevel();
        player.setLocation(new Location(level.getSpawnX(), level.getSpawnY(), level.getSpawnZ(), 0F, 90F, false)); // TODO: This is a temp solution
        if (!level.hasSavedPlayerData(player.getProfile().getUniqueId())) player.savePlayerData(level);

        player.readPlayerData(level.getPlayerData(player.getProfile().getUniqueId()).orElseThrow());

        player.setGameMode(GameMode.SPECTATOR);

        Dimension dimension = new Dimension();
        JoinGame packet = new JoinGame();
        packet.setEntityId(player.getEntityId());
        packet.setGamemode(player.getGameMode());             // TODO: Implement Gamemode
        packet.setPreviousGamemode(player.getGameMode());
        packet.setWorlds(new String[]{"world"});            // TODO: Implement worlds
        packet.setDimensionCodec(dimension.getCodec());
        packet.setDimension(dimension.getType());
        packet.setWorldName("world");                       // TODO: Implement worlds
        packet.setHashedSeed(12345678);
        packet.setMaxPlayers(connection.getProtocol().getMaxPlayers());
        packet.setViewDistance(10);                         // TODO: Implement view distance
        packet.setSimulationDistance(10);                   // TODO: Implement simulation distance
        packet.setReducedDebugInfo(false);
        packet.setRespawnScreen(true);
        packet.setDebug(false);
        packet.setFlat(false);
        connection.sendPacket(packet);

        connection.sendPacket(new SPluginMessage(connection.getProtocol().getSlpData().getVersion().getName()));
        connection.sendPacket(new PlayerPositionAndLook(player.getLocation()));
        startKeepAlive();
        Orion.getLogger().debug("Player {} has logged in at {}", player.getProfile().getUsername(), player.getLocation());


        // Player has joined, send first chunk
        connection.sendPacket(new ChunkData(connection.getProtocol().getLevelManager().getChunk(player.getLocation())));
    }

    private void startKeepAlive() {
        keepAliveTask = Orion.getScheduler().scheduleAtFixedRate(() -> {
            if (!connection.isActive())
                return;

            KeepAliveOut keepAlive = new KeepAliveOut();
            connection.sendPacket(keepAlive);
            keepAliveId = keepAlive.getId();
            Orion.getLogger().debug("Send keepalive to {} ({})", connection.getAddress(), player.getProfile().getUsername());
        }, 0, (25 * OrionScheduler.TPS)); // Every 25 seconds (30 seconds resulted in random timeouts)
    }

    @Override
    public void onDisconnect() {
        Orion.getScheduler().cancel(keepAliveTask);
    }

    @Override
    public void handle(KeepAliveIn packet) {
        if (keepAliveId != packet.getKeepAliveId()) connection.disconnect("<red>Invalid Keep Alive ID</red>");
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
    }

    @Override
    public void handle(CPluginMessage packet) {
        if (packet.getChannel().equals("minecraft:brand"))
            player.setBrand(new String(packet.getData(), StandardCharsets.UTF_8));
        else System.out.println(packet.getChannel());
    }

    @Override
    public void handle(PlayerPosition packet) {
        Location to = player.getLocation();
        to.setX(packet.getX());
        to.setY(packet.getY());
        to.setZ(packet.getZ());
        player.setLocation(playerMove(to, player.getLocation()));
    }

    @Override
    public void handle(PlayerRotation packet) {
        Location to = player.getLocation();
        to.setYaw(packet.getYaw());
        to.setPitch(packet.getPitch());
        to.setOnGround(packet.isOnGround());
        player.setLocation(playerMove(to, player.getLocation()));
    }

    @Override
    public void handle(PlayerPositionAndRotation packet) {
        Location to = player.getLocation();
        to.setX(packet.getX());
        to.setY(packet.getY());
        to.setZ(packet.getZ());
        to.setYaw(packet.getYaw());
        to.setPitch(packet.getPitch());
        to.setOnGround(packet.isOnGround());
        player.setLocation(playerMove(to, player.getLocation()));
    }

    @Override
    public void handle(TeleportConfirm packet) {
    }

    private Location playerMove(Location to, Location from) {
        if (to.equals(from)) return from;
        PlayerMoveEvent event = new PlayerMoveEvent(player, to, player.getLocation());
        Orion.getEventBus().post(event);
        if (!event.isCancelled()) {
            // if (!event.getTo().equals(to)) // TODO: implement teleporting
            return to;
        } else {
            // TODO: teleport player back to their original location
            return from;
        }
    }
}
