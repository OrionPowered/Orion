package pro.prysm.orion.server.data.palette;

import pro.prysm.orion.server.net.PacketByteBuf;

public class DirectPalette extends Palette {
    public DirectPalette() {
        this.bitsPerEntry = 15;
    }

    @Override
    public void write(PacketByteBuf buf) {
        // No data to write.
    }
}
