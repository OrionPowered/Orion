package pro.prysm.orion.server.world.anvilworld;

import lombok.Getter;
import net.kyori.adventure.nbt.BinaryTagIO;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import pro.prysm.orion.api.data.Block;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.util.CollectorUtil;
import pro.prysm.orion.common.OrionExceptionHandler;
import pro.prysm.orion.common.scheduler.OrionScheduler;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.world.Chunk;
import pro.prysm.orion.server.world.World;
import pro.prysm.orion.server.world.dimension.Dimension;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Getter
public class ImplWorld implements World {
    private final Path worldPath;
    private final String name;
    private final Dimension dimension;
    private final Boolean hardcore;
    private final Location spawn;
    private CompoundBinaryTag levelData;

    private final Set<ImplChunk> chunkCache;

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

        chunkCache = Collections.synchronizedSet(new HashSet<>());

        // Check cache for outdated chunks every 1 minute
        Orion.getScheduler().scheduleAtFixedRate(cacheTask(), 0, 60 * OrionScheduler.TPS);
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
        return chunkCache.stream()
                .filter(c -> c.getX() == x && c.getZ() == z)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    ImplChunk chunk;
                    if (list.size() == 0) {
                        try {
                            Optional<CompoundBinaryTag> nbt = getChunkNBT(x, z);
                            if (nbt.isPresent()) {
                                chunk = new ImplChunk(nbt.orElseThrow());
                                chunkCache.add(chunk);
                            }
                            else return CompletableFuture.completedFuture(Chunk.empty());
                        } catch (IOException e) {
                            OrionExceptionHandler.error(e);
                            return CompletableFuture.failedFuture(e);
                        }
                    } else chunk = list.get(0);
                    chunk.setLastAccessed(System.currentTimeMillis());
                    return CompletableFuture.completedFuture(chunk);
                }));
    }

    private Optional<CompoundBinaryTag> getChunkNBT(int x, int z) throws IOException {
        byte[] data = getChunkData(worldPath.resolve("region").resolve(String.format("r.%d.%d.mca", x >> 5, z >> 5)).toString(), x, z);
        if (data.length <= 0) return Optional.empty();
        else {
            ByteArrayInputStream is = new ByteArrayInputStream(data);
            return Optional.of(BinaryTagIO.reader().read(is));
        }
    }

    private native byte[] getChunkData(String filePath, int x, int z);

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

    @Override
    public Block getBlockAt(int x, int y, int z) {
        Block block = null;
        try {
            Chunk chunk = getChunkAsync(x >> 4, z >> 4).get();
            block = chunk.getBlockAt(x & 15, y, z & 15);
        } catch (ExecutionException | InterruptedException e) {
            OrionExceptionHandler.error(e);
        }
        return block;
    }

    private Runnable cacheTask() {
        return () -> {
            Orion.getLogger().debug("Running chunk cache TTL check task");
            long ttl = System.currentTimeMillis() - 60000; // cache TTL = 1 min
            chunkCache.stream().filter(chunk -> chunk.getLastAccessed() < ttl).forEach(chunkCache::remove);
        };
    }
}