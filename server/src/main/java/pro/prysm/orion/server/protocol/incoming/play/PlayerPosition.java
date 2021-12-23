package pro.prysm.orion.server.protocol.incoming.play;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

public class PlayerPosition extends IncomingPacket {
    private double x, y, z;
    private boolean onGround;

    public PlayerPosition(Connection connection) {
        super(connection);
        id = 0x11;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public boolean isOnGround() {
        return onGround;
    }

    @Override
    public void read(ByteBuf buf) {
        x = buf.readDouble();
        y = buf.readDouble();
        z = buf.readDouble();
        onGround = buf.readBoolean();
        connection.getHandler().handle(this);
    }
}
