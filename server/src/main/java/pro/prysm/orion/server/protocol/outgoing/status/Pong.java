package pro.prysm.orion.server.protocol.outgoing.status;

import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

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
