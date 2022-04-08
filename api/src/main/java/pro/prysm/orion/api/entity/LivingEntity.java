package pro.prysm.orion.api.entity;

import pro.prysm.orion.api.math.Velocity;

public interface LivingEntity extends Entity {
    /**
     * Retrieves the current velocity (movement) of this entity.
     * @return This entity's {@link Velocity}
     */
    Velocity getVelocity();

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
}
