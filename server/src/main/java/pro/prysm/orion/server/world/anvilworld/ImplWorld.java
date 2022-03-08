package pro.prysm.orion.server.world.anvilworld;

import lombok.Getter;
import net.kyori.adventure.nbt.BinaryTagIO;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.common.OrionExceptionHandler;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.world.Chunk;
import pro.prysm.orion.server.world.World;
import pro.prysm.orion.server.world.dimension.Dimension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
public class ImplWorld implements World {
    private final Path worldPath;
    private final String name;
    private final Dimension dimension;
    private final Boolean hardcore;
    private final Location spawn;
    private CompoundBinaryTag levelData;

    public ImplWorld(Path worldPath) {
        this.worldPath = worldPath;
        generate(worldPath);
        try {
            levelData = BinaryTagIO.reader().read(worldPath.resolve("level.dat"), BinaryTagIO.Compression.GZIP).getCompound("Data");
        } catch (IOException e) {
            OrionExceptionHandler.error("Failed to read level.dat", e);
        }
        name = levelData.getString("LevelName");
        dimension = Dimension.OVERWORLD;
        hardcore = levelData.getBoolean("hardcore");

        spawn = new Location(
                levelData.getInt("SpawnX"),
                levelData.getInt("SpawnY"),
                levelData.getInt("SpawnZ"),
                levelData.getFloat("SpawnAngle"),
                0.0F,
                false
        );
    }

    private void generate(Path path) {
        try {
            Files.createDirectories(path);
            Files.createDirectories(path.resolve("region"));
            Files.createDirectories(path.resolve("playerdata"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getSeedHash() {
        return 0;
    }

    @Override
    public boolean isHardcore() {
        return hardcore;
    }

    @Override
    public CompletableFuture<Chunk> getChunkAsync(int x, int z) {
        return new ImplRegion(this, x >> 5, z >> 5).getChunkAsync(x, z); // Temporary
    }

    @Override
    public boolean hasSavedPlayerData(UUID uuid) {
        File file = new File(String.format("%s/playerdata/%s.dat", worldPath.toString(), uuid));
        return file.exists() && file.isFile();
    }

    @Override
    public Optional<CompoundBinaryTag> getPlayerData(UUID uuid) {
        String fileName = String.format("%s.dat", uuid);
        try {
            return Optional.of(BinaryTagIO.reader().read(worldPath.resolve("playerdata").resolve(fileName), BinaryTagIO.Compression.GZIP));
        } catch (IOException e) {
            Orion.getLogger().warn("Failed to load {}", fileName);
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void savePlayerData(UUID uuid, CompoundBinaryTag tagData) {
        String fileName = String.format("%s.dat", uuid);

        try {
            BinaryTagIO.writer().write(tagData, Path.of(worldPath.toString(), "playerdata", fileName), BinaryTagIO.Compression.GZIP);
        } catch (IOException e) {
            Orion.getLogger().warn("Failed to save {}", fileName);
            e.printStackTrace();
        }
    }
}