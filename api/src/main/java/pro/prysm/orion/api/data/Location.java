package pro.prysm.orion.api.data;

public class Location {
    private double x, y, z;
    private float yaw, pitch;
    private boolean onGround;

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

    public Location() {
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        else if (o instanceof Location) {
            Location loc = (Location) o;
            return (loc.getX() == x) &&
                    (loc.getY() == y) &&
                    (loc.getZ() == z) &&
                    (loc.getYaw() == yaw) &&
                    (loc.getPitch() == pitch) &&
                    (loc.isOnGround() == onGround);
        } else return false;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("Location{");
        builder.append("x=").append(x).append(", ");
        builder.append("y=").append(y).append(", ");
        builder.append("z=").append(z).append(", ");
        builder.append("yaw=").append(yaw).append(", ");
        builder.append("pitch=").append(pitch).append(", ");
        builder.append("onGround=").append(onGround);
        builder.append("}");
        return builder.toString();
    }
}
