package pro.prysm.orion.server.protocol.incoming.play;

import lombok.Getter;
import pro.prysm.orion.api.protocol.incoming.play.PlayerRotationPacket;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

@Getter
public class PlayerRotation extends IncomingPacket implements PlayerRotationPacket {
    private float yaw, pitch;
    private boolean onGround;

    public PlayerRotation(Connection connection) {
        super(connection);
    }

    @Override
    public void read(PacketByteBuf buf) {
        yaw = buf.readFloat();
        pitch = buf.readFloat();
        onGround = buf.readBoolean();
        connection.getHandler().handle(this);
    }
}
