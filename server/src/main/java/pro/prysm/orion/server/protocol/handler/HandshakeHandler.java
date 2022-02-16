package pro.prysm.orion.server.protocol.handler;

import pro.prysm.orion.common.net.Connection;
import pro.prysm.orion.common.protocol.incoming.handshaking.Handshake;

public class HandshakeHandler extends AbstractHandler {
    public HandshakeHandler(Connection connection) {
        super(connection);
    }

    @Override
    public void handle(Handshake packet) {
        connection.setState(packet.getNextState());
    }
}
