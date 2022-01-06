package pro.prysm.orion.server.data.palette;

import pro.prysm.orion.server.net.PacketByteBuf;

public abstract class Palette {
    protected byte bitsPerEntry;

    public static Palette from(byte bitsPerEntry) {
        if (bitsPerEntry <= 4) return new IndirectPalette((byte) 4);
        else if (bitsPerEntry <= 8) return new IndirectPalette(bitsPerEntry);
        else return new DirectPalette();
    }

    public abstract void write(PacketByteBuf buf);
}
