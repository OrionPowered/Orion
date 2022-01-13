package pro.prysm.orion.server.entity.player;

import com.alexsobiek.anvil.Chunk;
import com.alexsobiek.anvil.Level;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.ListBinaryTag;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.sound.SoundStop;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.TitlePart;
import org.jetbrains.annotations.NotNull;
import pro.prysm.orion.api.data.*;
import pro.prysm.orion.api.entity.Player;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.entity.ImplLivingEntity;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.outgoing.play.ChunkData;
import pro.prysm.orion.server.util.TagUtil;
import pro.prysm.orion.server.world.LevelManager;

import java.util.Queue;

// TODO: Fully implement methods from Audience
// TODO: Write JavaDoc comments
@Getter
@Setter
public class ImplPlayer extends ImplLivingEntity implements Player {
    private final Connection connection;
    private final GameProfile profile;
    private ClientSettings settings;
    private Location location;
    private GameMode gameMode;
    private String brand;

    ImplPlayer(Connection connection, GameProfile profile, int entityId) {
        super(entityId, profile.getUniqueId(), EntityType.PLAYER.getId()); // Should I be using profile's uuid for this?
        this.connection = connection;
        this.profile = profile;
    }

    public void readPlayerData(CompoundBinaryTag nbt) {
        // Parse Location
        ListBinaryTag pos = nbt.getList("Pos");
        ListBinaryTag rot = nbt.getList("Rotation");
        location = new Location(
                new double[]{
                        pos.getDouble(0),
                        pos.getDouble(1),
                        pos.getDouble(2)
                },
                new float[]{
                        rot.getFloat(0),
                        rot.getFloat(1)
                },
                nbt.getByte("OnGround") == 0x0
        );

        // TODO: Parse rest of player data file
    }

    public void savePlayerData(Level level) {
        CompoundBinaryTag.Builder tagBuilder = CompoundBinaryTag.builder();

        // TODO: Write rest of player data
        tagBuilder.put("Pos", TagUtil.doubleList(location.getX(), location.getY(), location.getZ()));
        tagBuilder.put("Rotation", TagUtil.floatList(location.getYaw(), location.getPitch()));
        // ...
        tagBuilder.putBoolean("OnGround", location.isOnGround());

        level.savePlayerData(uuid(), tagBuilder.build());
    }

    public void sendChunks(Queue<Chunk> chunks) {
        chunks.parallelStream().dropWhile(chunk -> !chunk.exists()).forEach(this::sendChunk);
    }

    public void sendChunkAsync(LevelManager levelManager, int x, int z) {
        levelManager.getChunkAsync(x, z)
                .thenAcceptAsync(this::sendChunk)
                .join();
    }

    public void sendChunk(Chunk chunk) {
        sendChunkData(new ChunkData(chunk));
    }

    public void sendChunk(LevelManager levelManager, int x, int z) {
        sendChunk(levelManager.getChunk(x, z));
    }

    private void sendChunkData(ChunkData data) {
        if (data.exists()) connection.sendPacket(data);
        else Orion.getLogger().warn("Missing chunk at {}, {}", data.getX(), data.getZ());
    }

    // ===============================================================================================================
    // Kyori Adventure Identity Implementation
    // ===============================================================================================================

    @Override
    public java.util.@NotNull UUID uuid() {
        return profile.getUniqueId();
    }

    // ===============================================================================================================
    // Kyori Adventure Audience Implementation
    // ===============================================================================================================

    @Override
    public void sendMessage(final @NotNull Identity source, final @NotNull Component message, final @NotNull MessageType type) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void sendActionBar(final @NotNull Component message) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void sendPlayerListHeaderAndFooter(final @NotNull Component header, final @NotNull Component footer) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public <T> void sendTitlePart(final @NotNull TitlePart<T> part, final @NotNull T value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void clearTitle() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void resetTitle() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void showBossBar(final @NotNull BossBar bar) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void hideBossBar(final @NotNull BossBar bar) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void playSound(final @NotNull Sound sound) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void playSound(final @NotNull Sound sound, final double x, final double y, final double z) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void stopSound(final @NotNull SoundStop stop) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void openBook(final @NotNull Book book) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
