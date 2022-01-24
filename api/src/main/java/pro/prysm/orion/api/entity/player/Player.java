package pro.prysm.orion.api.entity.player;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.entity.LivingEntity;
import pro.prysm.orion.api.net.Connection;

/**
 * An interface to represent a player.
 */
public interface Player extends Identity, LivingEntity, Audience {
    /**
     * Retrieves this player's current gamemode.
     *
     * @return This player's current {@link GameMode}
     */
    GameMode getGameMode();

    /**
     * Retrieves the connection attached to this player.
     *
     * @return This player's {@link Connection}
     */
    Connection getConnection();

    /**
     * Retrieves this player's profile.
     *
     * @return This player's {@link GameProfile}
     */
    GameProfile getProfile();

    /**
     * Retrieves this player's client settings.
     *
     * @return This player's {@link ClientSettings}
     */
    ClientSettings getSettings();

    /**
     * Retrieves the client brand of this player.
     *
     * @return The client's brand
     */
    String getBrand();

    /**
     * Retrieves the player's latency
     *
     * @return Latency integer
     */
    int getLatency();

    /**
     * Retrieves the player's hidden status
     *
     * @return boolean
     */
    boolean isHidden();

    /**
     * Sets the player's hidden status
     *
     * @param hidden hidden status
     */
    void setHidden(boolean hidden);

    /**
     * Retrieves the display name component of this player
     *
     * @return The player's display name component
     */
    Component getDisplayName();

    /**
     * Sets the display name component of this player
     */
    void setDisplayName(Component displayName);

    /**
     * Teleports the player to the provided location
     *
     * @param location Location to teleport this player to
     */
    void teleport(Location location);
}
