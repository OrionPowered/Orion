package pro.prysm.orion.server.data.palette;

import pro.prysm.orion.server.net.PacketByteBuf;

public class DirectPalette extends Palette {
    public DirectPalette(int[] data) {
        super(data);
    }

    @Override
    public void write(PacketByteBuf buf) {
        // No data
    }
}
