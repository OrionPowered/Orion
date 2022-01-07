package pro.prysm.orion.api.protocol.incoming.play;

public interface PlayerPositionAndRotationPacket {
    double getX();

    double getY();

    double getZ();

    float getYaw();

    float getPitch();

    boolean isOnGround();
}
