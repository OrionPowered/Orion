package pro.prysm.orion.server.protocol.incoming;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.server.protocol.Packet;

public abstract class IncomingPacket extends Packet {
    public abstract void read(ByteBuf buf);
}
