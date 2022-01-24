package pro.prysm.orion.server.world;

import net.kyori.adventure.nbt.ListBinaryTag;
import pro.prysm.orion.api.data.Biome;

import java.util.BitSet;

public interface ChunkSection {

    /**
     * Gets the Y level of this ChunkSection
     *
     * @return Integer
     */
    int getY();

    /**
     * Gets the bits per block packed long
     *
     * @return Integer
     */
    int getBitsPerBlock();

    /**
     * Gets the bits per biome packed long
     *
     * @return Integer
     */
    int getBitsPerBiome();

    /**
     * Gets the total non-air blocks in this ChunkSection
     *
     * @return Short
     */
    short getBlockCount();

    /**
     * Returns the packed long array for block states
     *
     * @return packed long[]
     */
    long[] getBlockStates();

    /**
     * Gets the packed long array for biomes
     *
     * @return packed long[]
     */
    long[] getBiomes();

    /**
     * Gets the block state palette in NBT format
     *
     * @return ListBinaryTag NBT
     */
    ListBinaryTag getBlockStatePalette();

    /**
     * Gets the biome palette in NBT format
     *
     * @return ListBinaryTag NBT
     */
    ListBinaryTag getBiomePalette();

    /**
     * Gets the block state at the provided index
     *
     * @param index index to get block state from
     * @return Integer
     */
    int getBlockState(int index);

    /**
     * Gets the {@link Biome} at the provided index
     *
     * @param index index to get block state from
     * @return Biome
     */
    Biome getBiomeEntry(int index);

    /**
     * Gets the block light data
     *
     * @return byte[]
     */
    byte[] getBlockLight();

    /**
     * Gets the skylight data
     *
     * @return byte[]
     */
    byte[] getSkyLight();

    /**
     * Gets the bit set of the block light data
     *
     * @return BitSet
     */
    BitSet getBlockLightMask();

    /**
     * Gets the empty bit set of the block light data
     *
     * @return BitSet
     */
    BitSet getBlockLightEmptyMask();

    /**
     * Gets the bit set of the skylight data
     *
     * @return BitSet
     */
    BitSet getSkyLightMask();

    /**
     * Gets the empty bit set of the skylight data
     *
     * @return BitSet
     */
    BitSet getSkyLightEmptyMask();


}
