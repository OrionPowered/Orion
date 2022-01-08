package pro.prysm.orion.server.protocol.outgoing.play;

import lombok.Getter;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

@Getter
public class PluginMessageOut extends OutgoingPacket  {
    private final String channel;
    private final byte[] data;


    public PluginMessageOut(String channel, byte[] data) {
        super(0x18);
        this.channel = channel;
        this.data = data;
    }


    @Override
    public void write(PacketByteBuf buf) {
        buf.writeString(channel);
        buf.writeByteArray(data);
    }
}
