package pro.prysm.orion.server.protocol.handler;

import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.incoming.status.Ping;
import pro.prysm.orion.server.protocol.outgoing.status.Pong;

public class StatusHandler extends ProtocolHandler {
    public StatusHandler(Connection connection) {
        super(connection);
    }

    @Override
    public void handle(Ping packet) {
        connection.sendPacket(new Pong(packet.getValue()));
    }
}
