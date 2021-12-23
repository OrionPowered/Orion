package pro.prysm.orion.server.protocol.incoming;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.server.net.Connection;

public class IgnoredPacket extends IncomingPacket {
    public IgnoredPacket(Connection connection) {
        super(connection);
        connection.getCtx().flush();
    }

    @Override
    public void read(ByteBuf buf) {

    }
}
