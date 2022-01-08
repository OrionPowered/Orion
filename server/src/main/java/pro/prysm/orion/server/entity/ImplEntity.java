package pro.prysm.orion.server.entity;

import lombok.*;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.entity.Entity;

@Getter
@ToString
@EqualsAndHashCode
public class ImplEntity implements Entity {
    /** The next entity id to be used */
    private static int nextEntityId = 0;

    /** The entity id of this entity */
    private int entityId;
    /** The {@link Location} of this entity */
    @Setter
    private Location location;

    public ImplEntity() {
        this.entityId = useEntityId();
    }

    /**
     * Peeks the next entity id without using it.
     * @return The next entity id
     */
    public static int peekNextEntityId() {
        return nextEntityId;
    }

    /**
     * Uses an entity id; retrieves the next entity id and increments {@link ImplEntity#nextEntityId} by 1.
     * @return The available entity id
     */
    public static int useEntityId() {
        nextEntityId++;
        return nextEntityId - 1;
    }
}
