package pro.prysm.orion.server.event.events;

import pro.prysm.orion.server.event.Cancellable;
import pro.prysm.orion.server.event.Event;
import pro.prysm.orion.server.protocol.Packet;

public abstract class PacketEvent extends Event implements Cancellable {
    private boolean cancel;
    private Packet packet;

    public PacketEvent(Packet packet) {
        this.packet = packet;
    }

    public Packet getPacket() {
        return packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    public static class Incoming extends PacketEvent {

        public Incoming(Packet packet) {
            super(packet);
        }
    }

    public static class Outgoing extends PacketEvent {

        public Outgoing(Packet packet) {
            super(packet);
        }
    }
}
