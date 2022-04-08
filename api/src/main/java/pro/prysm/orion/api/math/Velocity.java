package pro.prysm.orion.api.math;

public interface Velocity {
    // TODO: JavaDoc
    float getX();

    float getY();

    float getZ();

    Vec3D getVector();

    long deltaTime();

    double distance();
}
