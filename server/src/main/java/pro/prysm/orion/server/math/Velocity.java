package pro.prysm.orion.server.math;

import lombok.*;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.math.Vec3D;
import pro.prysm.orion.api.math.Vec3DPoint;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Velocity implements pro.prysm.orion.api.math.Velocity {
    public static final Velocity NONE = new Velocity();
    private final long tolerance = 1000; // 1 second
    private Vec3D vector;
    private long startTime;
    private long endTime;
    private float x;
    private float y;
    private float z;

    public Velocity() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public double distance() {
        return vector.distance();
    }

    public long deltaTime() {
        return endTime - startTime;
    }

    public void set(float xMot, float yMot, float zMot) {
        this.x = xMot;
        this.y = yMot;
        this.z = zMot;
    }

    public void move(Location location) {
        long time = System.currentTimeMillis();
        Vec3DPoint end = new Vec3DPoint(location);
        if (vector == null || (time > (endTime + tolerance))) { // No velocity
            vector = new Vec3D(end, end);
            startTime = time;
            endTime = time;
        } else { // Within time allowance
            Vec3DPoint start = vector.getEnd();
            vector = new Vec3D(start, end);
            startTime = endTime;
            endTime = time;
            calculate();
        }
    }

    private void calculate() {
        Vec3DPoint start = vector.getStart();
        Vec3DPoint end = vector.getEnd();
        long deltaTime = deltaTime();
        if (deltaTime > 0) {
            x = ((float) (end.getX() - start.getX()) / deltaTime) * 100;
            y = ((float) (end.getY() - start.getY()) / deltaTime) * 100;
            z = ((float) (end.getZ() - start.getZ()) / deltaTime) * 100;
        }
    }

    @Override
    public String toString() {
        return String.format("x=%f, y=%f, z=%f, dist=%f\n", x, y ,z, distance());
    }
}