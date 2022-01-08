package pro.prysm.orion.server.entity;

import lombok.Data;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.entity.Entity;

@Data
public class ImplEntity implements Entity {
    /** The next entity id to be used */
    private static int nextEntityId;

    /** The entity id of this entity */
    private int entityId;
    /** The {@link Location} of this entity */
    private Location location;

    /**
     * Peeks the next entity id without using it.
     * @return The next entity id
     */
    public int peekNextEntityId() {
        return nextEntityId;
    }

    /**
     * Uses an entity id; retrieves the next entity id and increments {@link ImplEntity#nextEntityId} by 1.
     * @return The available entity id
     */
    public int useEntityId() {
        nextEntityId++;
        return nextEntityId - 1;
    }
}
