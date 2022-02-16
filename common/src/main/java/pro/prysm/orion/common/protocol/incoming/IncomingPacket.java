package pro.prysm.orion.common.protocol.incoming;

import lombok.Getter;
import pro.prysm.orion.common.net.Connection;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.Packet;
import pro.prysm.orion.server.protocol.Packet;

@Getter
public abstract class IncomingPacket extends Packet {
    protected Connection connection;

    public IncomingPacket(Connection connection) {
        this.connection = connection;
    }

    public abstract void read(PacketByteBuf buf);
}
