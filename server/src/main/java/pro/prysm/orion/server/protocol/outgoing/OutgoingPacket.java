package pro.prysm.orion.server.protocol.outgoing;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.server.protocol.Packet;

public abstract class OutgoingPacket extends Packet implements pro.prysm.orion.api.protocol.outgoing.OutgoingPacket {
    public void writeId(ByteBuf buf) {
        writeVarInt(getId(), buf);
    }
    public abstract void write(ByteBuf buf);
}
