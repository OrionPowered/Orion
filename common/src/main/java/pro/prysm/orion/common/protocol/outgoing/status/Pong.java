package pro.prysm.orion.common.protocol.outgoing.status;

import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.outgoing.OutgoingPacket;

public class Pong extends OutgoingPacket {
    private final long value;

    public Pong(long value) {
        super(0x01);
        this.value = value;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeLong(value);
    }
}
