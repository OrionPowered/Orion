package pro.prysm.orion.common.protocol.incoming.status;

import pro.prysm.orion.common.net.Connection;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.incoming.IncomingPacket;

public class Request extends IncomingPacket {
    public Request(Connection connection) {
        super(connection);
    }

    @Override
    public void read(PacketByteBuf buf) {
        connection.getHandler().handle(this);
    }
}
