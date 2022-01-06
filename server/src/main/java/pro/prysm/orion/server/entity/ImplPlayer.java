package pro.prysm.orion.server.entity;

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
import pro.prysm.orion.api.data.ClientSettings;
import pro.prysm.orion.api.data.GameProfile;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.entity.Player;
import pro.prysm.orion.server.net.Connection;

// TODO: Fully implement methods from Audience
// TODO: Write JavaDoc comments
public class ImplPlayer implements Player {
    private final Connection connection;
    private final GameProfile profile;
    private ClientSettings settings;
    private Location location;
    private String brand;

    public ImplPlayer(Connection connection, GameProfile profile) {
        this.connection = connection;
        this.profile = profile;
        this.location = new Location();
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

    public Connection getConnection() {
        return connection;
    }

    public GameProfile getProfile() {
        return profile;
    }

    public ClientSettings getSettings() {
        return settings;
    }

    public void setSettings(ClientSettings settings) {
        this.settings = settings;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    }

    @Override
    public void sendActionBar(final @NotNull Component message) {

    }

    @Override
    public void sendPlayerListHeaderAndFooter(final @NotNull Component header, final @NotNull Component footer) {
    }

    @Override
    public <T> void sendTitlePart(final @NotNull TitlePart<T> part, final @NotNull T value) {

    }

    @Override
    public void clearTitle() {

    }

    @Override
    public void resetTitle() {

    }

    @Override
    public void showBossBar(final @NotNull BossBar bar) {

    }

    @Override
    public void hideBossBar(final @NotNull BossBar bar) {

    }

    @Override
    public void playSound(final @NotNull Sound sound) {

    }

    @Override
    public void playSound(final @NotNull Sound sound, final double x, final double y, final double z) {

    }

    @Override
    public void stopSound(final @NotNull SoundStop stop) {

    }

    @Override
    public void openBook(final @NotNull Book book) {

    }
}
