package pro.prysm.orion.server.entity;

import pro.prysm.orion.api.data.EntityType;

import java.util.UUID;

public class EntityFactory {
    public static ImplEntity newEntity(EntityType type) {
        return new ImplEntity(ImplEntity.useEntityId(), UUID.randomUUID(), type.getId());
    }
}
