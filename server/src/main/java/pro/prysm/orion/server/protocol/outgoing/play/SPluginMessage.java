package pro.prysm.orion.server.protocol.outgoing.play;

import lombok.Getter;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

@Getter
public class SPluginMessage extends OutgoingPacket {
    private final String channel;
    private final String brand;
    private final byte[] data;

    public SPluginMessage(String channel, byte[] data) {
        super(0x18);
        this.channel = channel;
        this.brand = null;
        this.data = data;
    }

    public SPluginMessage(String brand) {
        super(0x18);
        this.channel = "minecraft:brand";
        this.brand = brand;
        this.data = new byte[0];
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeString(channel);
        if (brand == null) buf.writeBytes(data);
        else buf.writeString(brand);
    }
}
