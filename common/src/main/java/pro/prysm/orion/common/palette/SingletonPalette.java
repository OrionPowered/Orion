package pro.prysm.orion.common.palette;

import pro.prysm.orion.common.net.PacketByteBuf;

public class SingletonPalette extends Palette {
    private final int data;

    public SingletonPalette(int[] data) {
        super(data);
        this.data = (data.length > 0) ? data[0] : -1;
        if (data.length > 1) throw new IllegalStateException("Unexpected amount data received for SingletonPalette");
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeVarInt(data);
    }
}
