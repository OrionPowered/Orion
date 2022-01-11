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

    public void addX(int x) {
        this.x += x;
    }

    public void addY(int y) {
        this.y += y;
    }

    public void addZ(int z) {
        this.z += z;
    }

    public void subX(int x) {
        this.x -= x;
    }

    public void subY(int x) {
        this.y -= y;
    }

    public void subZ(int z) {
        this.z -= z;
    }

    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
