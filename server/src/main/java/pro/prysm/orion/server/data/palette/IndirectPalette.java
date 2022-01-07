package pro.prysm.orion.server.data.palette;

import pro.prysm.orion.server.net.PacketByteBuf;

public class IndirectPalette extends Palette {
    public IndirectPalette(byte bitsPerEntry) {
        this.bitsPerEntry = bitsPerEntry;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeVarInt(bitsPerEntry);
    }
}
