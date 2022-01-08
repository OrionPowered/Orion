package pro.prysm.orion.server.entity.player;

import com.alexsobiek.anvil.Level;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.nbt.*;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.sound.SoundStop;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.TitlePart;
import org.jetbrains.annotations.NotNull;
import pro.prysm.orion.api.data.ClientSettings;
import pro.prysm.orion.api.data.GameMode;
import pro.prysm.orion.api.data.GameProfile;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.entity.Player;
import pro.prysm.orion.server.entity.ImplEntity;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.util.TagUtil;

import java.util.UUID;

// TODO: Fully implement methods from Audience
// TODO: Write JavaDoc comments
@Getter
@Setter
public class ImplPlayer extends ImplEntity implements Player {
    private final Connection connection;
    private final GameProfile profile;
    private ClientSettings settings;
    private Location location;
    private GameMode gameMode;
    private String brand;

    public ImplPlayer(Connection connection, GameProfile profile, int entityId) {
        super(entityId, profile.getUniqueId()); // Should I be using profile's uuid for this?
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
