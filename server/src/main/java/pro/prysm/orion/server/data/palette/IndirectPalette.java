package pro.prysm.orion.server.data.palette;

import pro.prysm.orion.server.net.PacketByteBuf;

public class IndirectPalette extends Palette {
    public IndirectPalette(int[] data) {
        super(data);
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeVarInt(data.length);
        for(int d : data) buf.writeVarInt(d);
    }
}
