package pro.prysm.orion.server.data;

import com.alexsobiek.anvil.AnvilLib;
import com.alexsobiek.anvil.Chunk;
import com.alexsobiek.anvil.World;

import java.util.HashMap;

public class WorldManager {
    private final AnvilLib anvil;
    private HashMap<String, World> worlds;
    public Chunk test;

    public WorldManager() {
        anvil = new AnvilLib();
        worlds = new HashMap<>();
        World w = anvil.loadWorld("world");
        test = w.getRegion(0, 0).getChunk(0, 0);
    }

    public World loadWorld(String name) {
        if (worlds.containsKey(name)) return worlds.get(name);
        else {
            World world = anvil.loadWorld(name);
            worlds.put(world.getName(), world);
            return world;
        }
    }
}
