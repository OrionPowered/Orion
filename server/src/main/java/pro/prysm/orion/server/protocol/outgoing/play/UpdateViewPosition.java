package pro.prysm.orion.server.protocol.outgoing.play;

import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

public class UpdateViewPosition extends OutgoingPacket {
    private final int x, z;

    public UpdateViewPosition(int x, int z) {
        super(0x49);
        this.x = x;
        this.z = z;
    }

    @Override
    public void write(PacketByteBuf buf) {
        System.out.println("SENDING update view ");
        buf.writeVarInt(x);
        buf.writeVarInt(z);
    }
}
