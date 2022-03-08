package pro.prysm.orion.server.world;

import net.kyori.adventure.nbt.CompoundBinaryTag;

import java.util.BitSet;
import java.util.List;

public class EmptyChunk implements Chunk {
    /**
     * Gets X coordinate of this Chunk
     *
     * @return Integer - X Coordinate
     */
    @Override
    public int getX() {
        return 0;
    }

    /**
     * Gets Y coordinate of this Chunk
     *
     * @return Integer - Y Coordinate
     */
    @Override
    public int getZ() {
        return 0;
    }

    /**
     * Returns the status of this chunk
     *
     * @return ChunkStatus
     */
    @Override
    public ChunkStatus getStatus() {
        return ChunkStatus.EMPTY;
    }

    /**
     * Gets the bit set of the block light data
     *
     * @return BitSet
     */
    @Override
    public BitSet getBlockLightMask() {
        return null;
    }

    /**
     * Gets the empty bit set of the block light data
     *
     * @return BitSet
     */
    @Override
    public BitSet getBlockLightEmptyMask() {
        return null;
    }

    /**
     * Gets the bit set of the skylight data
     *
     * @return BitSet
     */
    @Override
    public BitSet getSkyLightMask() {
        return null;
    }

    /**
     * Gets the empty bit set of the skylight data
     *
     * @return BitSet
     */
    @Override
    public BitSet getSkyLightEmptyMask() {
        return null;
    }

    /**
     * Gets the HeightMaps in NBT format
     *
     * @return CompoundBinaryTag NBT
     */
    @Override
    public CompoundBinaryTag getHeightMaps() {
        return null;
    }

    /**
     * Gets a list of ChunkSections
     *
     * @return ChunkSection list
     */
    @Override
    public List<ChunkSection> getSections() {
        return null;
    }

    /**
     * Gets a chunk section
     *
     * @param index Index of chunk section
     * @return ChunkSection
     */
    @Override
    public ChunkSection getSection(int index) {
        return null;
    }
}
