package pro.prysm.orion.server.protocol.handler;

import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.incoming.handshaking.Handshake;

public class HandshakeHandler extends ProtocolHandler {
    public HandshakeHandler(Connection connection) {
        super(connection);
    }

    @Override
    public void handle(Handshake packet) {
        connection.setState(packet.getNextState());
    }
}
