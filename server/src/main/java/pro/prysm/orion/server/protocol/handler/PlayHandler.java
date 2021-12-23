package pro.prysm.orion.server.protocol.handler;

import pro.prysm.orion.server.Dimension;
import pro.prysm.orion.server.GameMode;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.outgoing.play.JoinGame;

public class PlayHandler extends ProtocolHandler {
    public PlayHandler(Connection connection) {
        super(connection);
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

}
