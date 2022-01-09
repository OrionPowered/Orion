package pro.prysm.orion.server.protocol.incoming.status;

import lombok.Getter;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

@Getter
public class Ping extends IncomingPacket {
    private long value;

    public Ping(Connection connection) {
        super(connection);
    }

    @Override
    public void read(PacketByteBuf buf) {
        value = buf.readLong();
        connection.getHandler().handle(this);
    }
}
