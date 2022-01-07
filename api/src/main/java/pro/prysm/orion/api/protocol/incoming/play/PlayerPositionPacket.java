package pro.prysm.orion.api.protocol.incoming.play;

public interface PlayerPositionPacket {
    double getX();

    double getY();

    double getZ();

    boolean isOnGround();
}
