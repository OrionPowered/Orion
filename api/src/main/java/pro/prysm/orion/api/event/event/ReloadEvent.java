package pro.prysm.orion.api.event.event;

import pro.prysm.orion.api.event.Event;

public class ReloadEvent implements Event {
    @Override
    public State getState() {
        return State.SINGLE;
    }
}
