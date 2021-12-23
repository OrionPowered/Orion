package pro.prysm.orion.server.world;

import pro.prysm.orion.api.exception.ResourceNotFoundException;
import pro.prysm.orion.server.Native;
import pro.prysm.orion.server.Orion;

import java.io.IOException;

public class ChunkManager {

    public ChunkManager() {
        try {
            Native.load("libAnvilLoader.so");
        } catch (ResourceNotFoundException | IOException e) {
            Orion.getLogger().severe("Failed to load native libraries!");
            e.printStackTrace();
            System.exit(1);
        }

        Orion.getLogger().info("Native chunk loader ready");
        loadRegion("world/region/r.0.0.mca");
    }

    public native void loadRegion(String name);
}
