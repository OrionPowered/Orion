package pro.prysm.orion.server.event.events;

import pro.prysm.orion.server.event.Cancellable;
import pro.prysm.orion.server.event.Event;

public class TestEvent extends Event implements Cancellable {
    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean cancel) {

    }
}
