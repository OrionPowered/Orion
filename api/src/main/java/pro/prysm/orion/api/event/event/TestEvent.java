package pro.prysm.orion.api.event.event;

import pro.prysm.orion.api.event.Cancellable;
import pro.prysm.orion.api.event.Event;

public class TestEvent implements Event, Cancellable {
    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean cancel) {

    }

    @Override
    public State getState() {
        return State.Single;
    }
}
