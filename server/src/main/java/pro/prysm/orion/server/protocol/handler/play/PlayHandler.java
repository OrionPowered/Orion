package pro.prysm.orion.server.protocol.handler.play;

import com.alexsobiek.anvil.Level;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import pro.prysm.orion.api.data.ChatMode;
import pro.prysm.orion.api.data.GameMode;
import pro.prysm.orion.api.data.Hand;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.entity.player.ImplPlayer;
import pro.prysm.orion.server.protocol.handler.ProtocolHandler;
import pro.prysm.orion.server.protocol.incoming.play.*;
import pro.prysm.orion.server.protocol.outgoing.play.JoinGame;
import pro.prysm.orion.server.protocol.outgoing.play.KeepAliveOut;
import pro.prysm.orion.server.protocol.outgoing.play.PlayerPositionAndLook;
import pro.prysm.orion.server.protocol.outgoing.play.PluginMessageOut;
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
    private long keepAliveId;

    public PlayHandler(ImplPlayer player) {
        super(player.getConnection());
        this.player = player;
        movement = new Movement(player);
        joinGame();
    }

    private void joinGame() {
        LevelManager levelManager = connection.getProtocol().getLevelManager();
        DimensionProvider dimension = levelManager.getDimension();
        Level level = levelManager.getLevel();

        player.setLocation(new Location(level.getSpawnX(), level.getSpawnY(), level.getSpawnZ(), 0F, 90F, false)); // TODO: This is a temp solution
        if (!level.hasSavedPlayerData(player.getProfile().getUniqueId())) player.savePlayerData(level);

        Optional<CompoundBinaryTag> playerData = level.getPlayerData(player.getProfile().getUniqueId());

        player.readPlayerData(playerData.orElseThrow());

        player.setGameMode(GameMode.SPECTATOR);

        JoinGame joinGame = new JoinGame();
        joinGame.setEntityId(player.getEntityId());
        joinGame.setGamemode(player.getGameMode());             // TODO: Implement Gamemode
        joinGame.setPreviousGamemode(player.getGameMode());
        joinGame.setWorlds(new String[]{"world"});            // TODO: Implement worlds
        joinGame.setDimensionCodec(dimension.getDimension());
        joinGame.setDimension(dimension.getDimensionType("minecraft:overworld"));
        joinGame.setWorldName(level.getName());                       // TODO: Implement worlds
        joinGame.setHashedSeed(12345678);
        joinGame.setMaxPlayers(connection.getProtocol().getMaxPlayers());
        joinGame.setViewDistance(10);                         // TODO: Implement view distance
        joinGame.setSimulationDistance(10);                   // TODO: Implement simulation distance
        joinGame.setReducedDebugInfo(false);
        joinGame.setRespawnScreen(true);
        joinGame.setDebug(false);
        joinGame.setFlat(false);
        connection.sendPacket(joinGame);

        connection.sendPacket(new PluginMessageOut("minecraft:brand", connection.getProtocol().getSlpData().getVersion().getName().getBytes(StandardCharsets.UTF_8)));
        connection.sendPacket(new PlayerPositionAndLook(player.getLocation()));
        startKeepAlive();
        Orion.getLogger().info("{}({}) has logged in", player.getProfile().getUsername(), connection.getAddress());
        Orion.getLogger().debug("Player {} has logged in at {}", player.getProfile().getUsername(), player.getLocation());
        movement.startChunkSending();
    }

    private void startKeepAlive() {
        keepAliveTask = Orion.getScheduler().scheduleAtFixedRate(() -> {
            if (!connection.isActive())
                return;

            KeepAliveOut keepAlive = new KeepAliveOut();
            connection.sendPacket(keepAlive);
            keepAliveId = keepAlive.getKeepAliveId();
            Orion.getLogger().debug("Sent keepalive to {} ({})", connection.getAddress(), player.getProfile().getUsername());
        }, 0, (25 * OrionScheduler.TPS)); // Every 25 seconds (30 seconds resulted in random timeouts)
    }

    @Override
    public void onDisconnect() {
        Orion.getScheduler().cancel(movement.getChunkTask());
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
    public void handle(PluginMessageIn packet) {
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
        player.setLocation(movement.playerMove(to, player.getLocation()));
    }

    @Override
    public void handle(PlayerRotation packet) {
        Location to = player.getLocation();
        to.setYaw(packet.getYaw());
        to.setPitch(packet.getPitch());
        to.setOnGround(packet.isOnGround());
        player.setLocation(movement.playerMove(to, player.getLocation()));
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
        player.setLocation(movement.playerMove(to, player.getLocation()));
    }

    @Override
    public void handle(TeleportConfirm packet) {
    }
}
