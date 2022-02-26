package pro.prysm.orion.common.protocol.incoming.play;

import lombok.Getter;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.incoming.IncomingPacket;

@Getter
public class Pong extends IncomingPacket {
    private long time;

    @Override
    public void read(PacketByteBuf buf) {
        time = buf.readLong();
    }
}
