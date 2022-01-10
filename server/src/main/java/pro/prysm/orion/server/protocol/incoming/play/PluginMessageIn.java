package pro.prysm.orion.server.protocol.incoming.play;

import lombok.Getter;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

@Getter
public class PluginMessageIn extends IncomingPacket {
    private String channel;
    private byte[] data;

    public PluginMessageIn(Connection connection) {
        super(connection);
    }

    @Override
    public void read(PacketByteBuf buf) {
        channel = buf.readString();
        data = buf.readByteArray();
        connection.getHandler().handle(this);
    }
}
