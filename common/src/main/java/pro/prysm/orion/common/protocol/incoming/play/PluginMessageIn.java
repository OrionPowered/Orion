package pro.prysm.orion.common.protocol.incoming.play;

import lombok.Getter;
import pro.prysm.orion.common.net.Connection;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.incoming.IncomingPacket;

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
        data = new byte[buf.readableBytes()];
        buf.readBytes(data);
        connection.getHandler().handle(this);
    }
}
