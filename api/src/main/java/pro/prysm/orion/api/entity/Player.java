package pro.prysm.orion.api.entity;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.identity.Identity;
import pro.prysm.orion.api.data.ClientSettings;
import pro.prysm.orion.api.data.GameProfile;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.net.Connection;

public interface Player extends Entity, Identity, Audience {
    Connection getConnection();

    GameProfile getProfile();

    ClientSettings getSettings();

    String getBrand();
}
