package pro.prysm.orion.server.protocol.incoming.login;

import lombok.Getter;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

@Getter
public class LoginStart extends IncomingPacket {
    private String username;

    public LoginStart(Connection connection) {
        super(connection);
    }

    @Override
    public void read(PacketByteBuf buf) {
        username = buf.readString();
        connection.getHandler().handle(this);
    }
}
