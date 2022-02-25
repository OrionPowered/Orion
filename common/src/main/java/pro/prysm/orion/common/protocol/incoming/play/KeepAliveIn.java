package pro.prysm.orion.common.protocol.incoming.play;

import lombok.Getter;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.incoming.IncomingPacket;

@Getter
public class KeepAliveIn extends IncomingPacket {
    private long keepAliveId;

    @Override
    public void read(PacketByteBuf buf) {
        keepAliveId = buf.readLong();
    }
}
