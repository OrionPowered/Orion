package pro.prysm.orion.server.world;

import lombok.Getter;
import pro.prysm.orion.server.world.dimension.CraftDimension;
import pro.prysm.orion.server.world.dimension.DimensionProvider;

import java.util.UUID;

@Getter
public class DefaultVoidProvider implements LevelProvider {
    private final World voidWorld;
    public final World[] worlds;
    public final String[] worldNames;
    DimensionProvider dimensionProvider;

    public DefaultVoidProvider() {
        voidWorld = new VoidWorld();
        worlds = new World[]{voidWorld};
        worldNames = new String[]{voidWorld.getName()};
        dimensionProvider = new CraftDimension();
    }

    @Override
    public World getWorld(String name) {
        return voidWorld;
    }

    @Override
    public World getWorldForPlayer(UUID uuid) {
        return voidWorld;
    }
}