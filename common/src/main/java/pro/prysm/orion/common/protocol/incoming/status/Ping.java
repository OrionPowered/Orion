package pro.prysm.orion.common.protocol.incoming.status;

import lombok.Getter;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.incoming.IncomingPacket;

@Getter
public class Ping extends IncomingPacket {
    private long value;

    @Override
    public void read(PacketByteBuf buf) {
        value = buf.readLong();
    }
}
