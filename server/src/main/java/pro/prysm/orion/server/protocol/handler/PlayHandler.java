package pro.prysm.orion.server.protocol.handler;

import pro.prysm.orion.server.net.Connection;

public class PlayHandler extends ProtocolHandler {
    public PlayHandler(Connection connection) {
        super(connection);
        System.out.println(connection.getProfile().getUniqueId());
    }
}
