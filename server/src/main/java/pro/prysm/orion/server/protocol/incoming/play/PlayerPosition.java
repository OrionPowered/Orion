package pro.prysm.orion.server.protocol.incoming.play;

import lombok.Getter;
import pro.prysm.orion.api.protocol.incoming.play.PlayerPositionPacket;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

@Getter
public class PlayerPosition extends IncomingPacket implements PlayerPositionPacket {
    private double x, y, z;
    private boolean onGround;

    public PlayerPosition(Connection connection) {
        super(0x11, connection);
    }

    @Override
    public void read(PacketByteBuf buf) {
        x = buf.readDouble();
        y = buf.readDouble();
        z = buf.readDouble();
        onGround = buf.readBoolean();
        connection.getHandler().handle(this);
    }
}
