package pro.prysm.orion.server.data.palette;

import io.netty.buffer.ByteBuf;

public class DirectPalette extends Palette {
    public DirectPalette() {
        this.bitsPerEntry = 15;
    }

    @Override
    public void write(ByteBuf buf) {
        // No data to write.
    }
}
