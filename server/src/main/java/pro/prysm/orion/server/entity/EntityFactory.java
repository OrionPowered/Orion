package pro.prysm.orion.server.entity;

import pro.prysm.orion.api.data.EntityType;

import java.util.UUID;

public class EntityFactory {
    /**
     * Creates a new entity with the specified {@link EntityType}.
     *
     * @param type The type of entity to create
     * @return The created entity
     */
    public static ImplEntity newEntity(EntityType type) {
        return new ImplEntity(ImplEntity.useEntityId(), UUID.randomUUID(), type.getId());
    }

    /**
     * Creates a new entity with the specified {@link EntityType} and {@link UUID}.
     *
     * @param type       The type of entity to create
     * @param entityUUID The entity's UUID
     * @return The created entity
     */
    public static ImplEntity newEntity(EntityType type, UUID entityUUID) {
        return new ImplEntity(ImplEntity.useEntityId(), entityUUID, type.getId());
    }

    /**
     * Creates a new entity with the specified {@link EntityType}, entity id, and {@link UUID}.
     *
     * @param type       The type of entity to create
     * @param entityId   The entity's id
     * @param entityUUID The entity's UUID
     * @return The created entity
     */
    public static ImplEntity newEntity(EntityType type, int entityId, UUID entityUUID) {
        return new ImplEntity(entityId, entityUUID, type.getId());
    }
}
