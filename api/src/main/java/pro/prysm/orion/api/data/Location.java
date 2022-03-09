package pro.prysm.orion.api.data;

import lombok.*;


@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Location implements Cloneable {
    private double x, y, z;
    private float yaw, pitch;
    private boolean onGround;
    private int chunkX, chunkZ;
    private int regionX, regionZ;

    public Location(double x, double y, double z, float yaw, float pitch, boolean onGround) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }

    public Location(double[] position, float[] rotation, boolean onGround) {
        this(position[0], position[1], position[2], rotation[0], rotation[1], onGround);
    }
    public void set(double x, double y, double z) {
        setX(x);
        setZ(z);
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
        this.chunkX = (int) x >> 4;
        this.regionX = chunkX >> 5;
    }

    public void setZ(double z) {
        this.z = z;
        this.chunkZ = (int) z >> 4;
        this.regionZ = chunkZ >> 5;
    }

    public boolean isSameChunk(Location other) {
        if (chunkX != other.getChunkX()) return false;
        else return (chunkZ == other.getChunkZ());
    }

    @Override
    public Location clone() {
        try {
            return (Location) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new AssertionError();
        }
    }
}
