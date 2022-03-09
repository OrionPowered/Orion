package pro.prysm.orion.server.world.anvilworld;

import lombok.Getter;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import pro.prysm.orion.server.world.Chunk;
import pro.prysm.orion.server.world.ChunkSection;
import pro.prysm.orion.server.world.ChunkStatus;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Set;

@Getter
public class ImplChunk implements Chunk {
    public static final int HEIGHT = 384;
    public static final int WIDTH = 16;

    private final int x, z;
    private final CompoundBinaryTag nbt;
    private final ChunkStatus status;
    private final CompoundBinaryTag heightMaps;
    private final BitSet skyLightMask = new BitSet();
    private final BitSet blockLightMask = new BitSet();
    private final BitSet skyLightEmptyMask = new BitSet();
    private final BitSet blockLightEmptyMask = new BitSet();
    private final List<ChunkSection> sections;

    public ImplChunk(CompoundBinaryTag nbt) {
        this.nbt = nbt;

        this.x = nbt.getInt("xPos");
        this.z = nbt.getInt("zPos");
        status = ChunkStatus.valueOf(nbt.getString("Status").toUpperCase());
        heightMaps = nbt.getCompound("Heightmaps");
        sections = new ArrayList<>();

        ListBinaryTag sectionsNBT = nbt.getList("sections");
        for (int i = 0; i < sectionsNBT.size(); i++) {
            CompoundBinaryTag sectionNBT = sectionsNBT.getCompound(i);
            Set<String> keySet = sectionNBT.keySet();

            // Some mods hack in data in the chunk sections (such as starlight)
            // If the chunk section a mod has added does not contain the vanilla block_states and biomes keys,
            // it shouldn't be considered valid by Orion and ignored.
            if (keySet.contains("block_states") && keySet.contains("biomes")) {
                ChunkSection section = new ImplChunkSection(sectionsNBT.getCompound(i));
                sections.add(section);
            }
        }
        calculateLighting();
    }

    private void calculateLighting() {
        for (int i = 0; i < sections.size(); i++) {
            ChunkSection section = sections.get(i);

            // Sky light
            if (section.hasSkyLight()) skyLightMask.set(i);
            else skyLightEmptyMask.set(i);

            // Block light
            if (section.hasBlockLight()) blockLightMask.set(i);
            else blockLightEmptyMask.set(i);
        }
    }

    @Override
    public ChunkSection getSection(int i) {
        return sections.get(i);
    }

    public Block getBlockAt(int x, int y, int z) {
        int sectionIndex = (int) Math.floor((double) y / 16) + 4;
        ImplChunkSection section = (ImplChunkSection) sections.get(sectionIndex);
        return section.getBlockAt(x & 15, y & 15, z & 15);
    }
}