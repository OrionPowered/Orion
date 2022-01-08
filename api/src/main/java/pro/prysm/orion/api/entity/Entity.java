package pro.prysm.orion.api.entity;

import pro.prysm.orion.api.data.Location;

/**
 * An interface to represent an entity.
 * @author Jay
 */
public interface Entity {
    /**
     * Retrieves the entity id of this entity,
     * @return This entity's entity id
     */
    int getEntityId();

    /**
     * Retrieves the location of this entity (XYZ, Yaw, Pitch, OnGround)
     * @return This entity's location
     */
    Location getLocation();
}
