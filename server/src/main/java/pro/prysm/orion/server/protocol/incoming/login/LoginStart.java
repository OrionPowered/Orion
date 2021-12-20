package pro.prysm.orion.server.protocol.incoming.login;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

public class LoginStart extends IncomingPacket  {
    String username;

    public LoginStart(Connection connection) {
        super(connection);
    }

    @Override
    public void read(ByteBuf buf) {
        username = readString(buf);
        connection.disconnect(String.format("<color:#2fc1fa>Hello %s!</color>", username));
    }
}
