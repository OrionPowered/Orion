package pro.prysm.orion.server.protocol.incoming.login;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.Protocol;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

public class LoginStart extends IncomingPacket  {
    String username;

    public LoginStart(Protocol protocol, Connection connection) {
        super(protocol, connection);
    }

    @Override
    public void read(ByteBuf buf) {
        username = readString(buf);
        connection.disconnect(String.format("<color:#2fc1fa>Hello %s!</color>", username));
    }
}
