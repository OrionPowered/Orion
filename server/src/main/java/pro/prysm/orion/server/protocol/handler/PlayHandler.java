package pro.prysm.orion.server.protocol.handler;

import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.outgoing.play.JoinGame;

public class PlayHandler extends ProtocolHandler {
    public PlayHandler(Connection connection) {
        super(connection);
        connection.sendPacket(new JoinGame());
    }
}
