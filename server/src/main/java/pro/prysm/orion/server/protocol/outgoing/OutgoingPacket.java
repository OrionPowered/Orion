package pro.prysm.orion.server.protocol.outgoing;

import lombok.Getter;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.Packet;

@Getter
public abstract class OutgoingPacket extends Packet {
    private final int id;

    protected OutgoingPacket(int id) {
        this.id = id;
    }

    public void writeId(PacketByteBuf buf) {
        buf.writeVarInt(id);
    }

    public abstract void write(PacketByteBuf buf);
}
