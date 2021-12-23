package pro.prysm.orion.api.entity;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.identity.Identity;
import pro.prysm.orion.api.net.Connection;

public interface Player extends Identity, Audience {
    public Connection getConnection();
    // TODO: public GameProfile getProfile();
}
