package pro.prysm.orion.server.world;

import pro.prysm.orion.server.world.dimension.DimensionProvider;

import java.util.UUID;

public interface LevelProvider {
    DimensionProvider getDimensionProvider();

    World getWorld(String name);

    World getWorldForPlayer(UUID uuid);

    World[] getWorlds();

    String[] getWorldNames();
}
