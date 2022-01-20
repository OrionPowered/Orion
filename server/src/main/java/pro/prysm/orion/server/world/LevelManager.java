package pro.prysm.orion.server.world;

import com.alexsobiek.anvil.AnvilLib;
import com.alexsobiek.anvil.Chunk;
import com.alexsobiek.anvil.Level;
import com.alexsobiek.anvil.Region;
import lombok.Getter;
import lombok.Setter;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.util.ExceptionHandler;
import pro.prysm.orion.server.world.dimension.CraftDimension;
import pro.prysm.orion.server.world.dimension.DimensionProvider;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Getter
public class LevelManager {
    private AnvilLib anvil;
    private Level level;
    private final Set<String> worlds;
    private Map<int[], Region> regions;
    private final boolean voidWorld;
    @Setter
    private DimensionProvider dimension;

    public LevelManager(String world) {
        anvil = new AnvilLib();
        level = anvil.loadLevel(world);
        dimension = new CraftDimension();
        worlds = new HashSet<>();
        regions = new HashMap<>();
        voidWorld = false;
        worlds.add(level.getName());
    }

    public LevelManager() {
        worlds = new HashSet<>();
        worlds.add("void");
        dimension = new CraftDimension();
        voidWorld = true;
    }

    public String[] getWorlds() {
        return worlds.toArray(new String[]{});
    }

    public Region getRegion(int x, int z) throws IllegalAccessException {
        Orion.getLogger().debug("Getting region for level {} at {}, {}", level.getName(), x, z);
        if (!voidWorld) {
            int[] pos = new int[]{x, z};
            if (regions.containsKey(pos)) {
                return regions.get(pos);
            } else {
                Region region = level.getRegion(x, z);
                regions.put(pos, region);
                return region;
            }
        } else throw new IllegalAccessException("World is a void world");
    }

    public Queue<Chunk> getChunks(int minX, int minZ, int maxX, int maxZ) {
        Queue<Chunk> chunks = new LinkedList<>();
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z < maxZ; z++) {
                chunks.add(getChunk(x, z));
            }
        }
        return chunks;
    }

    public Chunk getChunk(int x, int z) {
        Orion.getLogger().debug("Getting chunk for level {} at {}, {}", level.getName(), x, z);
        Chunk chunk = null;
        try {
            chunk = getRegion(x >> 5, z >> 5).getChunk(x, z);
        } catch (IllegalAccessException e) {
            ExceptionHandler.error(e);
        }
        return chunk;
    }

    public CompletableFuture<Chunk> getChunkAsync(int x, int z) {
        Orion.getLogger().debug("Getting chunk for level {} at {}, {}", level.getName(), x, z);
        CompletableFuture<Chunk> chunk = null;
        try {
            chunk = getRegion(x >> 5, z >> 5).getChunkAsync(x, z);
        } catch (IllegalAccessException e) {
            ExceptionHandler.error(e);
        }
        return chunk;
    }
}
