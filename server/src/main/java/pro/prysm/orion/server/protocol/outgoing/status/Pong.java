package pro.prysm.orion.server.protocol.outgoing.status;


import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

public class Pong extends OutgoingPacket implements pro.prysm.orion.api.protocol.outgoing.status.Pong {
    private final long value;

    public Pong(long value) {
        this.value = value;
        id = 0x01;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeLong(value);
    }
}
