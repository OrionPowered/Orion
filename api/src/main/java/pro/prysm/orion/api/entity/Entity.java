package pro.prysm.orion.api.entity;

import net.kyori.adventure.identity.Identity;
import pro.prysm.orion.api.data.Location;

/**
 * An interface to represent an entity.
 *
 * @author Jay
 */
public interface Entity extends Identity {
    /**
     * Retrieves the entity id of this entity,
     *
     * @return This entity's entity id
     */
    int getEntityId();

    /**
     * Retrieves the UUID of this entity
     * @return UUID
     */
    java.util.UUID getUuid();

    /**
     * Retrieves the Entity Type ID
     * @return Integer
     */
    int getEntityTypeId();

    /**
     * Retrieves what type of entity this is.
     *
     * @return This entity's {@link EntityType}
     */
    EntityType getType();

    /**
     * Retrieves the location of this entity (XYZ, Yaw, Pitch, OnGround).
     *
     * @return This entity's {@link Location}
     */
    Location getLocation();

    void setLocation(double x, double y, double z);

    /**
     * Sets this entity's location.
     * @param location The {@link Location} to set
     */
    void setLocation(Location location);

    /**
     * Entity Categories
     */
    enum Category {
        PLAYER,
        HOSTILE,
        PASSIVE,
        MOB,
        ANIMAL,
        WATER_CREATURE,
        AMBIENT,
        LIVING,
        PROJECTILE,
        OTHER
    }
}
