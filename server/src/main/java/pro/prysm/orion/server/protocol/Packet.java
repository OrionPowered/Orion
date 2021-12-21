package pro.prysm.orion.server.protocol;

import pro.prysm.orion.api.protocol.PacketDirection;
import pro.prysm.orion.api.protocol.PacketState;

public abstract class Packet extends PacketWriter {
    protected PacketDirection direction;
    protected PacketState state;
    protected int id;
    protected Protocol protocol;

    public Packet(Protocol protocol) {
        this.protocol = protocol;
    }

    public PacketDirection getDirection() {
        return direction;
    }

    public PacketState getState() {
        return state;
    }

    public int getId() {
        return id;
    }
}
