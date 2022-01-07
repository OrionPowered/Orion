package pro.prysm.orion.server.protocol.handler;

import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.incoming.status.Ping;
import pro.prysm.orion.server.protocol.incoming.status.Request;
import pro.prysm.orion.server.protocol.outgoing.status.Pong;

public class StatusHandler extends ProtocolHandler {
    public StatusHandler(Connection connection) {
        super(connection);
    }

    @Override
    public void handle(Request packet) {
        connection.sendPacket(connection.getProtocol().generateSLP());
    }

    @Override
    public void handle(Ping packet) {
        Orion.getLogger().debug(String.format("%s has pinged", connection.getAddress()));
        connection.sendPacket(new Pong(packet.getValue()));
    }
}
