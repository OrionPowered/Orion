package pro.prysm.orion.common.palette;

import pro.prysm.orion.common.net.PacketByteBuf;

public class DirectPalette extends Palette {
    public DirectPalette(int[] data) {
        super(data);
    }

    @Override
    public void write(PacketByteBuf buf) {
        // No data
    }
}
