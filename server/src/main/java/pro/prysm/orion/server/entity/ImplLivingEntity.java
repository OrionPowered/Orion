package pro.prysm.orion.server.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import pro.prysm.orion.api.entity.LivingEntity;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ImplLivingEntity extends ImplEntity implements LivingEntity {
    protected float headPitch;

    public ImplLivingEntity(int entityId, UUID uuid, int entityTypeId) {
        super(entityId, uuid, entityTypeId);
    }
}
