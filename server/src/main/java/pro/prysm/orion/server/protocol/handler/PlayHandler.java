package pro.prysm.orion.server.protocol.handler;

import pro.prysm.orion.api.Orion;
import pro.prysm.orion.api.data.*;
import pro.prysm.orion.api.event.events.PlayerMoveEvent;
import pro.prysm.orion.server.data.*;
import pro.prysm.orion.server.entity.ImplPlayer;
import pro.prysm.orion.server.protocol.incoming.login.TeleportConfirm;
import pro.prysm.orion.server.protocol.incoming.play.PlayerPosition;
import pro.prysm.orion.server.protocol.incoming.play.PlayerPositionAndRotation;
import pro.prysm.orion.server.protocol.incoming.play.PlayerRotation;
import pro.prysm.orion.server.protocol.incoming.play.PluginMessage;
import pro.prysm.orion.server.protocol.outgoing.play.OutgoingChunk;
import pro.prysm.orion.server.protocol.outgoing.play.JoinGame;
import pro.prysm.orion.server.protocol.outgoing.play.PlayerPositionAndLook;

public class PlayHandler extends ProtocolHandler {
    private final ImplPlayer player;
    public PlayHandler(ImplPlayer player) {
        super(player.getConnection());
        this.player = player;
        joinGame();
    }

    private void joinGame() {
        Dimension dimension = new Dimension();
        JoinGame packet = new JoinGame();
        packet.setEntityId(-1);
        packet.setGamemode(GameMode.SPECTATOR);
        packet.setPreviousGamemode(GameMode.SPECTATOR);
        packet.setWorlds(new String[]{"world"});
        packet.setDimensionCodec(dimension.getCodec());
        packet.setDimension(dimension.getType());
        packet.setWorldName("world");
        packet.setHashedSeed(12345678);
        packet.setMaxPlayers(100);
        packet.setViewDistance(10);
        packet.setSimulationDistance(10);
        packet.setReducedDebugInfo(false);
        packet.setRespawnScreen(true);
        packet.setDebug(true);
        packet.setFlat(false);
        connection.sendPacket(packet);
        connection.sendPacket(new PlayerPositionAndLook(new Location(0, 120, 0, 0, 0, false)));
    }

    @Override
    public void handle(pro.prysm.orion.server.protocol.incoming.play.ClientSettings packet) {
        ClientSettings settings = new ClientSettings(
                packet.getLocale(),
                packet.getViewDistance(),
                (packet.getChatMode() == 0) ? ChatMode.ENABLED : (packet.getChatMode() == 1 ? ChatMode.COMMANDS_ONLY : ChatMode.HIDDEN),
                packet.isColoredChat(),
                packet.getSkinParts(),
                (packet.getMainHand() == 0) ? Hand.LEFT : Hand.RIGHT
        );
        player.setSettings(settings);
    }

    @Override
    public void handle(PluginMessage packet) {
        if (packet.getChannel().equals("minecraft:brand")) player.setBrand(new String(packet.getData()));
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
