package pro.prysm.orion.server.world;

import net.kyori.adventure.nbt.CompoundBinaryTag;

import java.util.BitSet;
import java.util.List;

public interface Chunk {
    /**
     * Gets X coordinate of this Chunk
     *
     * @return Integer - X Coordinate
     */
    int getX();

    /**
     * Gets Y coordinate of this Chunk
     *
     * @return Integer - Y Coordinate
     */
    int getZ();

    /**
     * Returns if this chunk exists or is empty
     *
     * @return boolean
     */
    boolean exists();

    /**
     * Returns if this chunk is fully generated
     *
     * @return boolean
     */
    boolean isFull();

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

    /**
     * Gets the HeightMaps in NBT format
     *
     * @return CompoundBinaryTag NBT
     */
    CompoundBinaryTag getHeightMaps();

    /**
     * Gets a list of ChunkSections
     *
     * @return ChunkSection list
     */
    List<ChunkSection> getSections();

    /**
     * Gets a chunk section
     *
     * @param index Index of chunk section
     * @return ChunkSection
     */
    ChunkSection getSection(int index);
}
