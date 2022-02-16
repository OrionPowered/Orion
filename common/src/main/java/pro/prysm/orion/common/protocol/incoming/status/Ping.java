package pro.prysm.orion.common.protocol.incoming.status;

import lombok.Getter;
import pro.prysm.orion.common.net.Connection;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.incoming.IncomingPacket;

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
