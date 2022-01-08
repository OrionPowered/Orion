package pro.prysm.orion.server.protocol.outgoing.play;

import lombok.Getter;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

@Getter
public class KeepAliveOut extends OutgoingPacket {
    private final long keepAliveId = System.currentTimeMillis();

    public KeepAliveOut() {
        super(0x21);
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeLong(id);
    }
}
