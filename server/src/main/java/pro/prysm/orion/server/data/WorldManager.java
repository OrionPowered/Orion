package pro.prysm.orion.server.data;

import com.alexsobiek.anvil.AnvilLib;
import com.alexsobiek.anvil.Chunk;
import com.alexsobiek.anvil.Level;
import com.alexsobiek.anvil.Region;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.server.Orion;

import java.util.HashMap;
import java.util.Map;

public class WorldManager {
    private final AnvilLib anvil;
    private final Level level;

    // Both region and chunk map keys are integer arrays with a length of 2 for x and z
    // TODO: Regions and chunks are added to these maps but never removed
    private final Map<int[], Region> regions; // Region cache
    private final Map<int[], Chunk> chunks; // Chunk cache

    public WorldManager(String world) {
        anvil = new AnvilLib();
        regions = new HashMap<>();
        chunks = new HashMap<>();
        level = anvil.loadLevel(world);
    }

    public Level getLevel() {
        return level;
    }

    public Region getRegion(int x, int z) {
        Orion.getLogger().debug(String.format("Getting region for level %s at %d, %d", level.getName(), x, z));
        int[] pos = new int[]{x, z};
        if (regions.containsKey(pos)) return regions.get(pos);
        else {
            Region region = level.getRegion(x, z);
            regions.put(pos, region);
            return region;
        }
    }

    public Chunk getChunk(Location location) {
        return getChunk((int) Math.floor(location.getX()) >> 5, (int) Math.floor(location.getZ()) >> 5);
    }

    public Chunk getChunk(int x, int z) {
        Orion.getLogger().debug(String.format("Getting chunk for level %s at %d, %d", level.getName(), x, z));
        int[] pos = new int[]{x, z};
        if (chunks.containsKey(pos)) return chunks.get(pos);
        else {
            Chunk chunk = getRegion(x >> 5, z >> 5).getChunk(x, z);
            chunks.put(pos, chunk);
            return chunk;
        }
    }


}
