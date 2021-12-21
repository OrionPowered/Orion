package pro.prysm.orion.api.event.events;

import pro.prysm.orion.api.event.Cancellable;
import pro.prysm.orion.api.event.Event;

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
