package pro.prysm.orion.server.world.dimension;

import net.kyori.adventure.nbt.CompoundBinaryTag;

public interface DimensionProvider {

    /**
     * Gets the dimension codec
     *
     * @return NBT
     */
    CompoundBinaryTag getDimension();

    /**
     * Gets the NBT data of a specific dimension
     *
     * @param dimension Dimension
     * @return NBT
     */
    CompoundBinaryTag getDimensionType(Dimension dimension);

    /**
     * Gets the NBT data of the provided biome
     *
     * @param name Biome
     * @return NBT
     */
    CompoundBinaryTag getBiomeType(String name);
}
