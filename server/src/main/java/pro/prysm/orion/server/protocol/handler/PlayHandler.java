package pro.prysm.orion.server.protocol.handler;

import pro.prysm.orion.server.data.*;
import pro.prysm.orion.server.entity.ImplPlayer;
import pro.prysm.orion.server.protocol.outgoing.play.JoinGame;

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
        packet.setGamemode(GameMode.CREATIVE);
        packet.setPreviousGamemode(GameMode.CREATIVE);
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
    }

    public void handle(pro.prysm.orion.server.protocol.incoming.play.ClientSettings packet) {
        ClientSettings settings = new ClientSettings(
                packet.getLocale(),
                packet.getViewDistance(),
                (packet.getChatMode() == 0) ? ChatMode.ENABLED : (packet.getChatMode() == 1 ? ChatMode.COMMANDS_ONLY : ChatMode.HIDDEN),
                packet.isColoredChat(),
                packet.getSkinParts(),
                (packet.getMainHand() == 0) ? MainHand.LEFT : MainHand.RIGHT
        );
        player.setSettings(settings);
        System.out.printf("%s joined the game with UUID %s and locale %s\n", player.getProfile().getUsername(), player.getProfile().getUniqueId(), player.getSettings().getLocale());
    }

}
