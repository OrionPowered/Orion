package pro.prysm.orion.server.protocol.incoming.play;

import lombok.Getter;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

@Getter
public class KeepAliveIn extends IncomingPacket {
    private long keepAliveId;

    public KeepAliveIn(Connection connection) {
        super(0x0F, connection);
    }

    @Override
    public void read(PacketByteBuf buf) {
        keepAliveId = buf.readLong();
    }
}
