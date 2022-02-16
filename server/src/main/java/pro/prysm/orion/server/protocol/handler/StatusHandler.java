package pro.prysm.orion.server.protocol.handler;

import pro.prysm.orion.server.Orion;
import pro.prysm.orion.common.net.Connection;
import pro.prysm.orion.common.protocol.incoming.status.Ping;
import pro.prysm.orion.common.protocol.incoming.status.Request;
import pro.prysm.orion.common.protocol.outgoing.status.Pong;

public class StatusHandler extends AbstractHandler {
    public StatusHandler(Connection connection) {
        super(connection);
    }

    @Override
    public void handle(Request packet) {
        Orion.getLogger().debug("{} has pinged", connection.getAddress());
        connection.sendPacket(Orion.getServer().getProtocol().generateSLP());
    }

    @Override
    public void handle(Ping packet) {
        connection.sendPacket(new Pong(packet.getValue()));
    }
}
