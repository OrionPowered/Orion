package pro.prysm.orion.server.protocol;

import io.netty.buffer.ByteBuf;

public abstract class Packet extends PacketWriter {
    private PacketDirection direction;
    private PacketState state;
    private int id;

    public PacketDirection getDirection() {
        return direction;
    }

    public PacketState getState() {
        return state;
    }

    public int getId() {
        return id;
    }

    public abstract void write(ByteBuf buf);

    public abstract void read(ByteBuf buf);
}
