package pro.prysm.orion.api.entity;

import net.kyori.adventure.identity.Identity;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.data.Velocity;

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
     * Retrieves the current velocity (movement) of this entity.
     * @return This entity's {@link Velocity}
     */
    Velocity getVelocity();

    /**
     * Sets the current velocity to the 3 provided axis values.
     * @param xMot X axis motion
     * @param yMot Y axis motion
     * @param zMot Z axis motion
     */
    void setVelocity(double xMot, double yMot, double zMot);

    /**
     * Sets the current velocity to the provided velocity.
     * @param velocity The {@link Velocity} to set
     */
    void setVelocity(Velocity velocity);

    /**
     * Retrieves this entity's fall distance.
     * @return This entity's fall distance
     */
    float getFallDistance();

    /**
     * Sets this entity's fall distance.
     * @param fallDistance The fall distance to set
     */
    void setFallDistance(float fallDistance);

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
