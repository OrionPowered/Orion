package pro.prysm.orion.server.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pro.prysm.orion.server.math.Velocity;
import pro.prysm.orion.api.entity.LivingEntity;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ImplLivingEntity extends ImplEntity implements LivingEntity {
    protected float headPitch;
    @Setter
    protected Velocity velocity = Velocity.NONE;
    @Setter
    protected float fallDistance;

    public ImplLivingEntity(int entityId, UUID uuid, int entityTypeId) {
        super(entityId, uuid, entityTypeId);
    }

    public void setVelocity(float xMot, float yMot, float zMot) {
        velocity.set(xMot, yMot, zMot);
    }
}
