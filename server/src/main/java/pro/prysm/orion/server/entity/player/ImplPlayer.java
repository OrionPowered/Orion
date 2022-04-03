package pro.prysm.orion.server.entity.player;

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
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.entity.EntityType;
import pro.prysm.orion.api.entity.player.*;
import pro.prysm.orion.api.event.event.PlayerSneakEvent;
import pro.prysm.orion.api.event.event.PlayerSprintEvent;
import pro.prysm.orion.common.message.ChatPosition;
import pro.prysm.orion.common.net.Connection;
import pro.prysm.orion.common.protocol.PlayerInfoAction;
import pro.prysm.orion.common.protocol.outgoing.play.*;
import pro.prysm.orion.common.util.TagUtil;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.Server;
import pro.prysm.orion.server.entity.ImplLivingEntity;
import pro.prysm.orion.server.protocol.bidirectional.PlayerAbilities;
import pro.prysm.orion.server.protocol.outgoing.ChunkWithLight;
import pro.prysm.orion.server.world.Chunk;
import pro.prysm.orion.server.world.World;

import java.util.List;
import java.util.concurrent.CompletableFuture;

// TODO: Fully implement methods from Audience
// TODO: Write JavaDoc comments
@Getter
@Setter
public class ImplPlayer extends ImplLivingEntity implements Player {
    private final Connection connection;
    private final GameProfile profile;
    private ClientSettings settings;
    private PlayerStatus status;
    private GameMode gameMode;
    private String brand;
    private int latency;
    private boolean hidden;
    private Component displayName;
    private World world;
    private boolean invulnerable;
    private boolean instantBreak;
    private boolean allowFlight;
    private boolean sneaking;
    private boolean sprinting;
    private boolean flying;
    private float flySpeed = 0.05F; // Default, vanilla fly speed
    private float fieldOfViewModifier = 0.1F; // Default, vanilla modifier

    ImplPlayer(Connection connection, GameProfile profile, int entityId) {
        super(entityId, profile.getUniqueId(), EntityType.PLAYER.getId()); // Should I be using profile's uuid for this?
        this.connection = connection;
        this.profile = profile;
        status = PlayerStatus.CONNECTING;
        latency = 0;
        displayName = Component.text(profile.getUsername());
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

    public void savePlayerData(World world) {
        CompoundBinaryTag.Builder tagBuilder = CompoundBinaryTag.builder();

        // TODO: Write rest of player data
        tagBuilder.put("Pos", TagUtil.doubleList(location.getX(), location.getY(), location.getZ()));
        tagBuilder.put("Rotation", TagUtil.floatList(location.getYaw(), location.getPitch()));
        // ...
        tagBuilder.putBoolean("OnGround", location.isOnGround());

        world.savePlayerData(uuid(), tagBuilder.build());
    }

    public CompletableFuture<Void> sendChunkAsync(int x, int z) {
        return world.getChunkAsync(x, z).thenApplyAsync(ChunkWithLight::new).thenAcceptAsync(connection::sendPacket);
    }

    public CompletableFuture<Void> sendChunkAsync(Chunk chunk) {
        return CompletableFuture.completedFuture(new ChunkWithLight(chunk)).thenAcceptAsync(connection::sendPacket);
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
        allowFlight = (gameMode == GameMode.CREATIVE);
    }

    @Override
    public void setDisplayName(Component displayName) {
        this.displayName = displayName;
        if (!hidden) {
            Server server = Orion.getServer();
            Orion.getProtocol().broadcastPacket(server.getPlayers(), new PlayerInfo(PlayerInfoAction.UPDATE_DISPLAY_NAME, List.of(this)));
        }
    }

    @Override
    public void teleport(Location location) {
        connection.sendPacket(new PlayerPositionAndLook(location));
    }

    @Override
    public void setAllowFlight(boolean allowed) {
        allowFlight = allowed;
        updateAbilities();
    }

    @Override
    public void setInstantBreak(boolean instantBreak) {
        this.instantBreak = instantBreak;
        updateAbilities();
    }

    @Override
    public void setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
        updateAbilities();
    }

    @Override
    public void setFlySpeed(float flySpeed) {
        this.flySpeed = flySpeed;
        updateAbilities();
    }

    @Override
    public void setFieldOfViewModifier(float fieldOfViewModifier) {
        this.fieldOfViewModifier = fieldOfViewModifier;
        updateAbilities();
    }


    public void setSneaking(boolean sneaking) {
        this.sneaking = sneaking;
        if (sneaking) Orion.getEventBus().post(new PlayerSneakEvent(this));
    }

    public void setSprinting(boolean sprinting) {
        this.sneaking = sprinting;
        if (sprinting) Orion.getEventBus().post(new PlayerSprintEvent(this));
    }

    private void updateAbilities() {
        PlayerAbilities.Outgoing p = new PlayerAbilities.Outgoing(invulnerable, flying, allowFlight, instantBreak, flySpeed, fieldOfViewModifier);
        System.out.println(p);
        connection.sendPacket(p);
    }

    public boolean isFalling() {
        return velocity.getY() < 0;
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
        if (type == MessageType.CHAT && settings.getChatMode() == ChatMode.ENABLED) { // If it's a chat message, we need to format it
            connection.sendPacket(new ChatMessageOut(ChatPosition.CHAT, source.uuid(), message));
        } else if (settings.getChatMode() != ChatMode.HIDDEN) { // If not, we don't need to format anything
            connection.sendPacket(new ChatMessageOut(ChatPosition.SYSTEM, source.uuid(), message));
        }
    }

    @Override
    public void sendActionBar(final @NotNull Component message) {
        connection.sendPacket(new ActionBar(message));
    }

    @Override
    public void sendPlayerListHeaderAndFooter(final @NotNull Component header, final @NotNull Component footer) {
        connection.sendPacket(new PlayerlistHeaderFooter(header, footer));
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
