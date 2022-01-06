package pro.prysm.orion.server.protocol.incoming;

import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.Packet;

public abstract class IncomingPacket extends Packet implements pro.prysm.orion.api.protocol.incoming.IncomingPacket {
    protected Connection connection;

    public IncomingPacket(Connection connection) {
        this.connection = connection;
    }

    public abstract void read(PacketByteBuf buf);
}
