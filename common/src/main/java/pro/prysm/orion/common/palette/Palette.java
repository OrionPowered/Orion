package pro.prysm.orion.common.palette;

import lombok.Getter;
import lombok.ToString;
import pro.prysm.orion.common.net.PacketByteBuf;

@Getter
@ToString
public abstract class Palette {
    protected final int[] data;

    public Palette(int[] data) {
        this.data = data;
    }

    public abstract void write(PacketByteBuf buf);
}
