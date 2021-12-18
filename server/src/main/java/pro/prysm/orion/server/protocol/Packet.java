package pro.prysm.orion.server.protocol;

import pro.prysm.orion.api.protocol.PacketDirection;
import pro.prysm.orion.api.protocol.PacketState;

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
}
