package pro.prysm.orion.server.protocol.incoming.status;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

public class Ping extends IncomingPacket implements pro.prysm.orion.api.protocol.incoming.status.Ping {
    private long value;
    public Ping(Connection connection) {
        super(connection);
        id = 0x01;
    }

    public long getValue() {
        return value;
    }

    @Override
    public void read(ByteBuf buf) {
        value = buf.readLong();
        connection.getHandler().handle(this);
    }
}