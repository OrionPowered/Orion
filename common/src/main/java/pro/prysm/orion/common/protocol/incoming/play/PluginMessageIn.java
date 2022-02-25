package pro.prysm.orion.common.protocol.incoming.play;

import lombok.Getter;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.incoming.IncomingPacket;

@Getter
public class PluginMessageIn extends IncomingPacket {
    private String channel;
    private byte[] data;

    @Override
    public void read(PacketByteBuf buf) {
        channel = buf.readString();
        data = new byte[buf.readableBytes()];
        buf.readBytes(data);
    }
}
