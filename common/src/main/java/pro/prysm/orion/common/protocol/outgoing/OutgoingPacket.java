package pro.prysm.orion.common.protocol.outgoing;

import lombok.Getter;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.Packet;

@Getter
public abstract class OutgoingPacket extends Packet {
    protected final int id;
    protected OutgoingPacket(int id) {
        this.id = id;
    }

    public void writeId(PacketByteBuf buf) {
        buf.writeVarInt(id);
    }

    public abstract void write(PacketByteBuf buf);
}
