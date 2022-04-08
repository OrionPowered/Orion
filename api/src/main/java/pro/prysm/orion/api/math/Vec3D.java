package pro.prysm.orion.api.math;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Vec3D {
    private Vec3DPoint start;
    private Vec3DPoint end;

    public double distance() {
        return start.distance(end);
    }
}
