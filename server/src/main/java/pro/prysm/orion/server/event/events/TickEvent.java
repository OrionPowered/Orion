package pro.prysm.orion.server.event.events;

import pro.prysm.orion.api.event.Event;

public class TickEvent implements Event {
    @Override
    public State getState() {
        return State.SINGLE;
    }
}
