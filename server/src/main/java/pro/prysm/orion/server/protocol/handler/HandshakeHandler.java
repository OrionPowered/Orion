package pro.prysm.orion.server.protocol.handler;

import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.incoming.status.Handshake;

public class HandshakeHandler extends ProtocolHandler {
    public HandshakeHandler(Connection connection) {
        super(connection);
    }

    @Override
    public void handle(Handshake packet) {
        if (packet.getNextState() == PacketState.STATUS) {
            Orion.getLogger().debug(String.format("%s has pinged", connection.getAddress()));
            connection.sendPacket(protocol.generateSLP());
        } else {
            connection.setState(packet.getNextState());
        }
    }
}
