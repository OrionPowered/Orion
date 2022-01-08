package pro.prysm.orion.server.protocol.incoming;

import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.Packet;

public abstract class IncomingPacket extends Packet {
    protected Connection connection;

    public IncomingPacket(int id, Connection connection) {
        super(id);
        this.connection = connection;
    }

    public abstract void read(PacketByteBuf buf);
}
