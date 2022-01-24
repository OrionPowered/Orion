package pro.prysm.orion.server.world;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import pro.prysm.orion.api.data.Location;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface World {
    /**
     * Gets the name of this world
     *
     * @return String
     */
    String getName();

    /**
     * Gets the spawn {@link Location} of this world
     *
     * @return Location
     */
    Location getSpawn();

    /**
     * Gets a {@link Chunk} asynchronously
     *
     * @param x X coordinate of Chunk (relative to world)
     * @param z Y coordinate of Chunk (relative to world)
     * @return Chunk
     */
    CompletableFuture<Chunk> getChunkAsync(int x, int z);

    /**
     * Returns if the provided UUID has saved player data
     *
     * @param uuid Player UUID
     * @return boolean
     */
    boolean hasSavedPlayerData(UUID uuid);

    /**
     * Gets the player data, if present, from the provided player UUID
     *
     * @param uuid Player UUID
     * @return Optional NBT
     */
    Optional<CompoundBinaryTag> getPlayerData(UUID uuid);

    /**
     * Saves the provided NBT player data
     *
     * @param uuid Player UUID
     * @param data NBT data to save
     */
    void savePlayerData(UUID uuid, CompoundBinaryTag data);
}
