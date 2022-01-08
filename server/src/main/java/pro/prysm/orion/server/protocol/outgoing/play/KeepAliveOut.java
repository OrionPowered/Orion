package pro.prysm.orion.server.protocol.outgoing.play;

import lombok.Getter;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

@Getter
public class KeepAliveOut extends OutgoingPacket {
    private final long keepAliveId;

    public KeepAliveOut() {
        super(0x21);
        keepAliveId = System.currentTimeMillis();
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeLong(id);
    }
}
