package pro.prysm.orion.server.protocol.outgoing.play;

import lombok.Getter;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

import java.nio.charset.StandardCharsets;

@Getter
public class SPluginMessage extends OutgoingPacket {
    private final String channel;
    private final byte[] data;

    public SPluginMessage(String channel, byte[] data) {
        super(0x18);
        this.channel = channel;
        this.data = data;
    }

    public SPluginMessage(String channel, String message) {
        this(channel, message.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeString(channel);
        buf.writeBytes(data);
    }
}
