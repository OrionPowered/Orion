package pro.prysm.orion.common.protocol.incoming.play;

import lombok.Getter;
import pro.prysm.orion.common.net.Connection;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.incoming.IncomingPacket;

@Getter
public class PlayerRotation extends IncomingPacket {
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
