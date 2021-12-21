package pro.prysm.orion.server.protocol.incoming;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.Packet;
import pro.prysm.orion.server.protocol.Protocol;

public abstract class IncomingPacket extends Packet {
    protected Connection connection;
    public IncomingPacket(Protocol protocol, Connection connection) {
        super(protocol);
        this.connection = connection;
    }

    public abstract void read(ByteBuf buf);
}
