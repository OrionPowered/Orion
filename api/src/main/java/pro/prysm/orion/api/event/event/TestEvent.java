package pro.prysm.orion.api.event.event;

import pro.prysm.orion.api.event.Cancellable;
import pro.prysm.orion.api.event.Event;

public class TestEvent extends Event implements Cancellable {
    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean cancel) {

    }
}
