package pro.prysm.orion.server.world;

import net.kyori.adventure.nbt.CompoundBinaryTag;

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
    boolean doesExist();

    /**
     * Returns if this chunk is fully generated
     *
     * @return boolean
     */
    boolean isFull();

    /**
     * Gets a list of byte arrays with sky light data
     *
     * @return Byte array list
     */
    List<byte[]> getSkyLight();

    /**
     * Gets a list of byte arrays with block light data
     *
     * @return Byte array list
     */
    List<byte[]> getBlockLight();

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
