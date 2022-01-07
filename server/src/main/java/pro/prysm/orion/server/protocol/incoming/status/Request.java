package pro.prysm.orion.server.protocol.incoming.status;

import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

public class Request extends IncomingPacket {
    public Request(Connection connection) {
        super(0x00, connection);
    }

    @Override
    public void read(PacketByteBuf buf) {
        connection.getHandler().handle(this);
    }
}
