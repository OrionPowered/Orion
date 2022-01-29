package pro.prysm.orion.server.protocol.incoming.play;

import lombok.Getter;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

@Getter
public class Pong extends IncomingPacket {
    private long time;

    public Pong(Connection connection) {
        super(connection);
    }

    @Override
    public void read(PacketByteBuf buf) {
        time = buf.readLong();
        connection.getHandler().handle(this);
    }
}
