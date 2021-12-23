package pro.prysm.orion.server.protocol.incoming.play;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

public class PluginMessage extends IncomingPacket {
    private String channel;
    private byte[] data;

    public PluginMessage(Connection connection) {
        super(connection);
        id = 0x0A;
    }

    public String getChannel() {
        return channel;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public void read(ByteBuf buf) {
        channel = readString(buf);
        data = readByteArray(buf);
        connection.getHandler().handle(this);
    }
}
