package pro.prysm.orion.server.protocol.outgoing;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.server.protocol.Packet;
import pro.prysm.orion.server.protocol.Protocol;

public abstract class OutgoingPacket extends Packet {
    public OutgoingPacket(Protocol protocol) {
        super(protocol);
    }

    public void writeId(ByteBuf buf) {
        writeVarInt(getId(), buf);
    }
    public abstract void write(ByteBuf buf);
}
