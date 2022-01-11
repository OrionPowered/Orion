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

@Getter
public class LevelManager {
    private final AnvilLib anvil;
    private final Level level;
    @Setter
    private DimensionProvider dimension;

    // Both region and chunk map keys are integer arrays with a length of 2 for x and z
    // TODO: Regions and chunks are added to these maps but never removed
    private final Map<int[], Region> regions = new HashMap<>(); // Region cache
    private final Map<int[], Chunk> chunks = new HashMap<>(); // Chunk cache

    public LevelManager(String world) {
        anvil = new AnvilLib();
        level = anvil.loadLevel(world);
        dimension = new CraftDimension();
    }

    public Region getRegion(int x, int z) {
        Orion.getLogger().debug("Getting region for level {} at {}, {}", level.getName(), x, z);
        //int[] pos = new int[]{x, z};
        //if (regions.containsKey(pos)) return regions.get(pos);
        //else {
            Region region = level.getRegion(x, z);
            //regions.put(pos, region);
            return region;
        //}
    }

    public Chunk getChunk(Location location) {
        return getChunk((int) Math.floor(location.getX()) >> 4, (int) Math.floor(location.getZ()) >> 4);
    }

    public Chunk getChunk(int x, int z) {
        Orion.getLogger().debug("Getting chunk for level {} at {}, {}", level.getName(), x, z);
        int[] pos = new int[]{x, z};
        //if (chunks.containsKey(pos)) return chunks.get(pos);
        //else {
            Chunk chunk = getRegion(x >> 5, z >> 5).getChunk(x, z);
            //chunks.put(pos, chunk);
            return chunk;
        //}
    }
}
