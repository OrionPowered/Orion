package pro.prysm.orion.server.protocol.incoming;

import lombok.Getter;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.incoming.IncomingPacket;

@Getter
public class PlayerMovement extends IncomingPacket {
    private boolean onGround;

    @Override
    public void read(PacketByteBuf buf) {
        onGround = buf.readBoolean();
    }
}