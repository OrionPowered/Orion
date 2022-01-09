package pro.prysm.orion.server.data.palette;

import lombok.Getter;
import lombok.ToString;
import pro.prysm.orion.server.net.PacketByteBuf;

@Getter
@ToString
public class PalettedContainer {

    private final int bitsPerEntry;
    private final long[] data;
    private final Palette palette;

    private PalettedContainer(int bitsPerEntry, long[] data, Palette palette) {
        this.bitsPerEntry = bitsPerEntry;
        this.data = data;
        this.palette = palette;
    }

    /**
     * Creates a new PalettedContainer.
     * See more: <a href="https://wiki.vg/Chunk_Format#Paletted_Container_structure">wiki.vg</a>
     *
     * @param type         PalettedContainer Type
     * @param bitsPerEntry bits per entry
     * @param paletteData  Palette data
     * @param data         Data to store/write
     * @return Creates a new PalettedContainer
     */
    public static PalettedContainer from(Type type, int bitsPerEntry, int[] paletteData, long[] data) {
        switch (type) {
            case BLOCK_STATES -> {
                return switch (bitsPerEntry) {
                    case 0 -> new PalettedContainer(bitsPerEntry, data, new SingletonPalette(paletteData));
                    case 1, 2, 3, 4 -> new PalettedContainer(4, data, new IndirectPalette(paletteData));
                    case 5, 6, 7, 8 -> new PalettedContainer(bitsPerEntry, data, new IndirectPalette(paletteData));
                    default -> new PalettedContainer(bitsPerEntry, data, new DirectPalette(paletteData));
                };
            }
            case BIOME -> {
                return switch (bitsPerEntry) {
                    case 0 -> new PalettedContainer(bitsPerEntry, data, new SingletonPalette(paletteData));
                    case 1, 2, 3 -> new PalettedContainer(bitsPerEntry, data, new IndirectPalette(paletteData));
                    default -> new PalettedContainer(bitsPerEntry, data, new DirectPalette(paletteData));
                };
            }
            default -> throw new IllegalStateException("Not a valid palette type: " + type);
        }
    }

    public void write(PacketByteBuf buf) {
        buf.writeVarInt(bitsPerEntry);
        palette.write(buf);
        buf.writeLongArray(data);
    }

    public enum Type {
        BLOCK_STATES,
        BIOME
    }
}
