package pro.prysm.orion.common.protocol.outgoing.play;

import lombok.Getter;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.outgoing.OutgoingPacket;

@Getter
public class Ping extends OutgoingPacket {
    private final long time;

    public Ping() {
        super(0x30);
        time = System.currentTimeMillis();
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeLong(time);
    }
}
