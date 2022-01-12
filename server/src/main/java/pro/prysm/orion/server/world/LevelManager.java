package pro.prysm.orion.server.world;

import com.alexsobiek.anvil.AnvilLib;
import com.alexsobiek.anvil.Chunk;
import com.alexsobiek.anvil.Level;
import com.alexsobiek.anvil.Region;
import lombok.Getter;
import lombok.Setter;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.world.dimension.CraftDimension;
import pro.prysm.orion.server.world.dimension.DimensionProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Getter
public class LevelManager {
    private final AnvilLib anvil;
    private final Level level;
    @Setter
    private DimensionProvider dimension;

    private final Map<int[], Region> regions;

    public LevelManager(String world) {
        anvil = new AnvilLib();
        level = anvil.loadLevel(world);
        dimension = new CraftDimension();
        regions = new HashMap<>();
    }



    public Region getRegion(int x, int z) {
        Orion.getLogger().debug("Getting region for level {} at {}, {}", level.getName(), x, z);
        int[] pos = new int[]{x, z};
        if (regions.containsKey(pos)) {
            return regions.get(pos);
        } else {
            Region region = level.getRegion(x, z);
            regions.put(pos, region);
            return region;
        }
    }

    public Chunk getChunk(int x, int z) {
        Orion.getLogger().debug("Getting chunk for level {} at {}, {}", level.getName(), x, z);
        return getRegion(x >> 5, z >> 5).getChunk(x, z);
    }

    public CompletableFuture<Chunk> getChunkAsync(int x, int z) {
        Orion.getLogger().debug("Getting chunk for level {} at {}, {}", level.getName(), x, z);
        return getRegion(x >> 5, z >> 5).getChunkAsync(x, z);
    }
}
