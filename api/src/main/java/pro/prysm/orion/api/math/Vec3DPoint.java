package pro.prysm.orion.api.math;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pro.prysm.orion.api.data.Location;

@Getter
@Setter
@AllArgsConstructor
public class Vec3DPoint {
    private double x;
    private double y;
    private double z;

    public Vec3DPoint(Location location) {
        x = location.getX();
        y = location.getY();
        z = location.getZ();
    }

    public Vec3DPoint(double[] array) {
        if (array.length > 3) throw new IllegalArgumentException("Array length must not be longer than 3");
        x = array[0];
        y = array[1];
        z = array[2];
    }

    public double distance(Vec3DPoint other) {
        return Math.sqrt(Math.pow(other.getX() - x, 2) + Math.pow(other.getY() - y, 2) + Math.pow(other.getZ() - z, 2));
    }

    public double[] toArray() {
        return new double[]{x, y, z};
    }
}