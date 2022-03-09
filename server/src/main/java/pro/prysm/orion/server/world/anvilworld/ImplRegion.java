package pro.prysm.orion.server.world.anvilworld;

import lombok.Getter;
import net.kyori.adventure.nbt.BinaryTagIO;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import pro.prysm.orion.common.OrionExceptionHandler;
import pro.prysm.orion.server.world.Chunk;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Getter
public class ImplRegion {
    private final Path regionPath;
    private final int x, z;

    public ImplRegion(ImplWorld world, int x, int z) {
        this.x = x;
        this.z = z;
        regionPath = world.getWorldPath().resolve("region").resolve(String.format("r.%d.%d.mca", x, z));
    }

    private Optional<CompoundBinaryTag> getChunkNBT(int x, int z) throws IOException {
        byte[] data = getChunkData(regionPath.toString(), x, z);
        if (data.length <= 0) return Optional.empty();
        else {
            ByteArrayInputStream is = new ByteArrayInputStream(data);
            return Optional.of(BinaryTagIO.reader().read(is));
        }

    }

    private native byte[] getChunkData(String filePath, int x, int z);

    public CompletableFuture<Chunk> getChunkAsync(int x, int z) {
        try {
            Optional<CompoundBinaryTag> nbt = getChunkNBT(x, z);
            return CompletableFuture.completedFuture((nbt.isPresent()) ? new ImplChunk(nbt.orElseThrow()) : Chunk.empty());
        } catch (IOException e) {
            OrionExceptionHandler.error(e);
            return CompletableFuture.failedFuture(e);
        }
    }
}