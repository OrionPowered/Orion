package pro.prysm.orion.server.event.events;

import pro.prysm.orion.server.event.Cancellable;
import pro.prysm.orion.server.event.Event;

public abstract class PacketEvent extends Event implements Cancellable {
    private boolean cancelled;

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
