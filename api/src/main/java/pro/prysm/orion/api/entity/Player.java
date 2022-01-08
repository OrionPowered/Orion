package pro.prysm.orion.api.entity;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.identity.Identity;
import pro.prysm.orion.api.data.ClientSettings;
import pro.prysm.orion.api.data.GameProfile;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.net.Connection;

/**
 * An interface to represent a player.
 */
public interface Player extends Entity, Identity, Audience {
    /**
     * Retrieves the {@link Connection} attached to this player.
     * @return This player's {@link Connection}
     */
    Connection getConnection();

    /**
     * Retrieves this player's {@link GameProfile}.
     * @return This player's {@link GameProfile}
     */
    GameProfile getProfile();

    /**
     * Retrieves this player's {@link ClientSettings}.
     * @return This player's {@link ClientSettings}
     */
    ClientSettings getSettings();

    /**
     * Retrieves the client brand of this player.
     * @return The client's brand
     */
    String getBrand();
}
