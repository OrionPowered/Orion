package pro.prysm.orion.server.protocol.incoming.login;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

public class LoginStart extends IncomingPacket implements pro.prysm.orion.api.protocol.incoming.login.LoginStart {
    private String username;

    public LoginStart(Connection connection) {
        super(connection);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void read(ByteBuf buf) {
        username = readString(buf);
        connection.getHandler().handle(this);
    }
}