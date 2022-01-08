package pro.prysm.orion.server.entity;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.entity.Entity;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class ImplEntity implements Entity {
    /** The next entity id to be used */
    private static int nextEntityId = 0;

    /** The entity id of this entity */
    protected final int entityId;
    /** The UUID of this entity */
    private final UUID uuid;
    /** The {@link Location} of this entity */
    @Setter
    protected Location location;

    @Override
    public java.util.@NotNull UUID uuid() {
        return uuid;
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
