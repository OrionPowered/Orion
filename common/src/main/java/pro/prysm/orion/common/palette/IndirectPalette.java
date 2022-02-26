package pro.prysm.orion.common.palette;

import pro.prysm.orion.common.net.PacketByteBuf;

public class IndirectPalette extends Palette {
    public IndirectPalette(int[] data) {
        super(data);
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeVarInt(data.length);
        for (int d : data) buf.writeVarInt(d);
    }
}
