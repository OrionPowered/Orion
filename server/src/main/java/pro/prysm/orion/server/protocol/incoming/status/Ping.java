package pro.prysm.orion.server.protocol.incoming.status;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.Protocol;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;
import pro.prysm.orion.server.protocol.outgoing.status.Pong;

public class Ping extends IncomingPacket {
    public Ping(Protocol protocol, Connection connection) {
        super(protocol, connection);
        id = 0x01;
    }

    @Override
    public void read(ByteBuf buf) {
        connection.sendPacket(new Pong(protocol, buf.readLong()));
    }
}
