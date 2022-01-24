package pro.prysm.orion.server.world;

import pro.prysm.orion.server.world.dimension.DimensionProvider;

public interface LevelProvider {
    DimensionProvider getDimensionProvider();

    World[] getWorlds();
}
