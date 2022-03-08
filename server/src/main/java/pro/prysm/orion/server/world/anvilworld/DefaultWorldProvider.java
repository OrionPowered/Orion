package pro.prysm.orion.server.world.anvilworld;

import lombok.Getter;
import lombok.Setter;
import pro.prysm.orion.api.exception.ResourceNotFoundException;
import pro.prysm.orion.common.NativeLibrary;
import pro.prysm.orion.common.OrionExceptionHandler;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.world.LevelProvider;
import pro.prysm.orion.server.world.World;
import pro.prysm.orion.server.world.dimension.CraftDimension;
import pro.prysm.orion.server.world.dimension.DimensionProvider;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.UUID;

@Getter
public class DefaultWorldProvider implements LevelProvider {
    private final DimensionProvider dimensionProvider;
    private final World[] worlds;

    public DefaultWorldProvider() {
        // Load Native Library
        try {
            new NativeLibrary("anvil").load();
        } catch(ResourceNotFoundException e) {
            OrionExceptionHandler.error("Failed to load anvil library, is your OS supported?", e);
        }
        // Setup Dimension
        dimensionProvider = new CraftDimension();

        // Setup world(s)
        String worldName = Orion.getServer().getConfig().getString("world.name");
        worlds = new World[]{new ImplWorld(Path.of(worldName))};
    }

    @Override
    public World getWorld(String s) {
        return Arrays.stream(worlds).filter(world -> world.getName().equals(s)).findFirst().orElse(null);
    }

    @Override
    public World getWorldForPlayer(UUID uuid) {
        return worlds[0];
    }

    @Override
    public World[] getWorlds() {
        return worlds;
    }

    @Override
    public String[] getWorldNames() {
        return Arrays.stream(worlds).map(World::getName).toArray(String[]::new);
    }
}
