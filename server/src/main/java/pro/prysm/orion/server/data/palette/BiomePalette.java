package pro.prysm.orion.server.data.palette;

import com.alexsobiek.anvil.Chunk;
import com.alexsobiek.anvil.ChunkSection;
import pro.prysm.orion.api.data.Biome;
import pro.prysm.orion.server.net.PacketByteBuf;

import java.util.Arrays;

public class BiomePalette {
    private final ChunkSection section;
    private final int size;
    //private final int bitsPerEntry;
    private final long[] data;

    public BiomePalette(ChunkSection section) {
        this.section = section;
        size = section.getBiomePalette().size();
        data = new long[Chunk.SECTION_WIDTH * Chunk.SECTION_WIDTH];
        if (size == 1) {
            //bitsPerEntry = 3; // Indirect
            int id = Biome.getBiome(section.getBiomePaletteEntry(0).replace("minecraft:", "")).getId();
            Arrays.fill(data, id);
        }
        //else bitsPerEntry = 0;
    }

    public void write(PacketByteBuf buf) {
        buf.writeByte(6);
        //if (bitsPerEntry != 3)  throw new IllegalStateException("Unimplemented bits per entry for Biome palette: " + bitsPerEntry);
        //buf.writeVarInt(data.length);
        //for(int d : data) buf.writeVarInt(d);
        buf.writeLongArray(data);
    }
}
