package pro.prysm.orion.api.data;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Velocity {
    /**
     * No motion, all values are 0.
     */
    public static final Velocity NONE = new Velocity();

    /**
     * Motion on the x axis.
     */
    private double x;
    /**
     * Motion on the y axis.
     */
    private double y;
    /**
     * Motion on the z axis.
     */
    private double z;

    public Velocity() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public void set(double xMot, double yMot, double zMot) {
        this.x = xMot;
        this.y = yMot;
        this.z = zMot;
    }
}
