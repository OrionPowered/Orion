package pro.prysm.orion.server.data.palette;

import lombok.Getter;
import pro.prysm.orion.server.net.PacketByteBuf;

@Getter
public abstract class Palette {
    enum Type {
        SINGLETON,
        INDIRECT,
        DIRECT
    }

    protected final int[] data;
    public Palette(int[] data) {
        this.data = data;
    }

    public abstract void write(PacketByteBuf buf);
}
