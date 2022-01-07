package pro.prysm.orion.api.protocol.incoming.play;

public interface PlayerRotationPacket {
    float getYaw();

    float getPitch();

    boolean isOnGround();
}
