package pro.prysm.orion.server.world.anvilworld;


import lombok.Getter;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import pro.prysm.orion.api.data.Biome;
import pro.prysm.orion.api.data.Block;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.world.ChunkSection;

@Getter
public class ImplChunkSection implements ChunkSection {
    public static final int HEIGHT = 16;
    private final int y;
    private final int bitsPerBlock;
    private final int bitsPerBiome;
    private final long[] biomes;
    private final short blockCount; // TODO: count non-air blocks
    private final long[] blockStates;
    private final ListBinaryTag blockStatePalette;
    private final ListBinaryTag biomePalette;
    private final byte[] blockLight;
    private final byte[] skyLight;

    public ImplChunkSection(CompoundBinaryTag nbt) {
        this.y = nbt.getInt("Y");

        // Block States
        CompoundBinaryTag statesCompound = nbt.getCompound("block_states");
        blockStates = statesCompound.getLongArray("data");
        blockStatePalette = statesCompound.getList("palette");

        // Biomes
        CompoundBinaryTag biomesCompound = nbt.getCompound("biomes");
        biomes = biomesCompound.getLongArray("data");
        biomePalette = biomesCompound.getList("palette");

        // The biome palette should NEVER be empty
        if (biomePalette.size() == 0) Orion.getLogger().warn("EMPTY BIOME PALETTE Y {}", this.y);

        // Lighting
        skyLight = nbt.getByteArray("SkyLight");
        blockLight = nbt.getByteArray("BlockLight");


        // CALCULATIONS
        // bitsPerBlock = (int) Math.ceil(Math.log(blockStatePalette.size()) / Math.log(2));
        bitsPerBlock = blockStates.length / 64;

        // We have to encode 64 biomes into an array of 64 bit longs. Therefore, the bits per biome will be proportional
        // to the amount of 64 bit longs stored. If there are 2 longs (giving us 128 bits), 64 biomes will be 2 bits per biome.
        //64 / biomes.length
        bitsPerBiome = biomes.length;

        if (blockStates.length == 0) blockCount = 0;
        else blockCount = (short) (ImplChunk.WIDTH * ImplChunk.WIDTH * HEIGHT);  // TODO: Calculate actual block count
    }

    @Override
    public int getBlockState(int i) {
        CompoundBinaryTag palette = blockStatePalette.getCompound(i);
        return Block.getBlock(palette.getString("Name").replace("minecraft:", "")).getDefaultState();
    }

    @Override
    public Biome getBiomeEntry(int i) {
        return Biome.getBiome(biomePalette.getString(i).replace("minecraft:", ""));
    }

    private int blockIndex(int x, int y, int z) {
        return (y & 0xf) << 8 | z << 4 | x;
    }

    public Block getBlockAt(int x, int y, int z) {
        if (blockStates.length == 0) return Block.AIR;
        int blockIndex = blockIndex(x, y, z);
        int startLong = (blockIndex * bitsPerBlock) / 64;
        int startOffset = (blockIndex * bitsPerBlock) % 64;
        int endLong = ((blockIndex + 1) * bitsPerBlock - 1) / 64;

        int paletteIndex = (int) blockStates[startLong] >> startOffset;
        if (startLong != endLong) paletteIndex |= blockStates[endLong] << (64 - startOffset);

        paletteIndex &= ((1 << bitsPerBlock) - 1);

        return Block.getBlock(blockStatePalette.getCompound(paletteIndex).getString("Name").replace("minecraft:", ""));
    }

    @Override
    public boolean hasBlockLight() {
        return blockLight.length != 0;
    }

    @Override
    public boolean hasSkyLight() {
        return skyLight.length != 0;
    }
}