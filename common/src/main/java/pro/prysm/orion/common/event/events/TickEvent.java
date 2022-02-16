package pro.prysm.orion.common.event.events;

import pro.prysm.orion.api.event.Event;

public class TickEvent implements Event {
    @Override
    public State getState() {
        return State.SINGLE;
    }
}
