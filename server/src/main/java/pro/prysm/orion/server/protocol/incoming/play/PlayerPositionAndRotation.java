package pro.prysm.orion.server.protocol.incoming.play;

import lombok.Getter;
import pro.prysm.orion.api.protocol.incoming.play.PlayerPositionAndRotationPacket;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

@Getter
public class PlayerPositionAndRotation extends IncomingPacket implements PlayerPositionAndRotationPacket {
    private double x, y, z;
    private float yaw, pitch;
    private boolean onGround;

    public PlayerPositionAndRotation(Connection connection) {
        super(connection);
        id = 0x12;
    }

    @Override
    public void read(PacketByteBuf buf) {
        x = buf.readDouble();
        y = buf.readDouble();
        z = buf.readDouble();
        yaw = buf.readFloat();
        pitch = buf.readFloat();
        onGround = buf.readBoolean();
        connection.getHandler().handle(this);
    }
}
