package pro.prysm.orion.common.protocol.outgoing.play;

import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.outgoing.OutgoingPacket;

public class UpdateViewPosition extends OutgoingPacket {
    private final int x, z;

    public UpdateViewPosition(int x, int z) {
        super(0x49);
        this.x = x;
        this.z = z;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeVarInt(x);
        buf.writeVarInt(z);
    }
}
