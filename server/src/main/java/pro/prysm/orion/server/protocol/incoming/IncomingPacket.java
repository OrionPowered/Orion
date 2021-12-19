package pro.prysm.orion.server.protocol.incoming;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.Packet;

public abstract class IncomingPacket extends Packet {
    protected Connection connection;
    public IncomingPacket(Connection connection) {
        this.connection = connection;
    }
    public abstract void read(ByteBuf buf);
}
