package pro.prysm.orion.server.protocol.incoming;

import lombok.Getter;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.incoming.IncomingPacket;

@Getter
public class PlayerPosition extends IncomingPacket {
    private double x, y, z;
    private boolean onGround;

    @Override
    public void read(PacketByteBuf buf) {
        x = buf.readDouble();
        y = buf.readDouble();
        z = buf.readDouble();
        onGround = buf.readBoolean();
    }
}
