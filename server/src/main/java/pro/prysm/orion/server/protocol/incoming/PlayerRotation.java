package pro.prysm.orion.server.protocol.incoming;

import lombok.Getter;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.incoming.IncomingPacket;

@Getter
public class PlayerRotation extends IncomingPacket {
    private float yaw, pitch;
    private boolean onGround;

    @Override
    public void read(PacketByteBuf buf) {
        yaw = buf.readFloat();
        pitch = buf.readFloat();
        onGround = buf.readBoolean();
    }
}
