package pro.prysm.orion.server.protocol.outgoing.status;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.server.protocol.Protocol;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

public class Pong extends OutgoingPacket implements pro.prysm.orion.api.protocol.outgoing.status.Pong {
    private final long value;
    public Pong(Protocol protocol, long value) {
        super(protocol);
        this.value = value;
        id = 0x01;
    }
    @Override
    public void write(ByteBuf buf) {
        buf.writeLong(value);
    }
}
