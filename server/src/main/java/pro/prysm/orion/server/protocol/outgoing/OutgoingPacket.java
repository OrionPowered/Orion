package pro.prysm.orion.server.protocol.outgoing;

import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.Packet;

public abstract class OutgoingPacket extends Packet {
    protected OutgoingPacket(int id) {
        super(id);
    }

    public void writeId(PacketByteBuf buf) {
        buf.writeVarInt(getId());
    }

    public abstract void write(PacketByteBuf buf);
}
