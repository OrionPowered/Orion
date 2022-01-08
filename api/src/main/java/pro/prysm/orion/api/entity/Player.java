package pro.prysm.orion.api.entity;

import net.kyori.adventure.audience.Audience;
import pro.prysm.orion.api.data.ClientSettings;
import pro.prysm.orion.api.data.GameMode;
import pro.prysm.orion.api.data.GameProfile;
import pro.prysm.orion.api.net.Connection;

/**
 * An interface to represent a player.
 */
public interface Player extends Entity, Audience {
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
}
