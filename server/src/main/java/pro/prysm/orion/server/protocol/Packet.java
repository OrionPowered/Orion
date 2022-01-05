package pro.prysm.orion.server.protocol;

import pro.prysm.orion.api.protocol.PacketDirection;
import pro.prysm.orion.api.protocol.PacketState;

public abstract class Packet implements pro.prysm.orion.api.protocol.Packet {
    protected PacketDirection direction;
    protected PacketState state;
    protected int id;

    @Override
    public PacketDirection getDirection() {
        return direction;
    }

    @Override
    public PacketState getState() {
        return state;
    }

    @Override
    public int getId() {
        return id;
    }
}
