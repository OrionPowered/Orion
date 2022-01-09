package pro.prysm.orion.server.data.palette;

import pro.prysm.orion.server.net.PacketByteBuf;

public class SingletonPalette extends Palette {
    public SingletonPalette(int[] data) {
        super(data);
        if (data.length > 1) throw new IllegalStateException("Unexpected amount data received for SingletonPalette");
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeVarInt(data[0]);
    }
}
