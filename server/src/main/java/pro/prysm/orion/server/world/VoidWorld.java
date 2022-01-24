package pro.prysm.orion.server.world;

import lombok.Getter;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.server.world.dimension.Dimension;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
public class VoidWorld implements World {
    private final String name;
    private final Location spawn;

    public VoidWorld() {
        name = "void";
        spawn = new Location(0, 120, 0, 0, 0, true);
    }

    /**
     * Returns true if this World is a void world.
     *
     * @return boolean
     */
    @Override
    public boolean isVoid() {
        return true;
    }

    /**
     * Gets this world's dimension
     *
     * @return Dimension
     */
    @Override
    public Dimension getDimension() {
        return Dimension.END;
    }

    /**
     * Returns the hashed seed
     *
     * @return long
     */
    @Override
    public long getSeedHash() {
        return 123456789;
    }

    /**
     * Returns true if this world is hardcore
     *
     * @return boolean
     */
    @Override
    public boolean isHardcore() {
        return false;
    }

    /**
     * Gets a {@link Chunk} asynchronously
     *
     * @param x X coordinate of Chunk (relative to world)
     * @param z Y coordinate of Chunk (relative to world)
     * @return Chunk
     */
    @Override
    public CompletableFuture<Chunk> getChunkAsync(int x, int z) {
        return null;
    }

    /**
     * Returns if the provided UUID has saved player data
     *
     * @param uuid Player UUID
     * @return boolean
     */
    @Override
    public boolean hasSavedPlayerData(UUID uuid) {
        return false;
    }

    /**
     * Gets the player data, if present, from the provided player UUID
     *
     * @param uuid Player UUID
     * @return Optional NBT
     */
    @Override
    public Optional<CompoundBinaryTag> getPlayerData(UUID uuid) {
        return Optional.empty();
    }

    /**
     * Saves the provided NBT player data
     *
     * @param uuid Player UUID
     * @param data NBT data to save
     */
    @Override
    public void savePlayerData(UUID uuid, CompoundBinaryTag data) {
        throw new UnsupportedOperationException("Void world does not have player data!");
    }
}
