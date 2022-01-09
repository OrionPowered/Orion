package pro.prysm.orion.api.data;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Location {
    private double x, y, z;
    private float yaw, pitch;
    private boolean onGround;

    public Location(double[] position, float[] rotation, boolean onGround) {
        this(position[0], position[1], position[2], rotation[0], rotation[1], onGround);
    }

    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
