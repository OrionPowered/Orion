package pro.prysm.orion.server.protocol.incoming;

import lombok.Getter;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.Packet;

@Getter
public abstract class IncomingPacket extends Packet {
    protected Connection connection;

    public IncomingPacket(Connection connection) {
        this.connection = connection;
    }

    public abstract void read(PacketByteBuf buf);
}
