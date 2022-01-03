package pro.prysm.orion.server.data.palette;

import io.netty.buffer.ByteBuf;

public abstract class Palette {
    protected byte bitsPerEntry;
    public abstract void write(ByteBuf buf);

    public static Palette from(byte bitsPerEntry) {
        if (bitsPerEntry <= 4) return new IndirectPalette((byte)4);
        else if (bitsPerEntry <= 8) return new IndirectPalette(bitsPerEntry);
        else return new DirectPalette();
    }
}
