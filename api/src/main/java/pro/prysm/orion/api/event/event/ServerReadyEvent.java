package pro.prysm.orion.api.event.event;

import pro.prysm.orion.api.event.Event;

public class ServerReadyEvent implements Event {
    @Override
    public State getState() {
        return State.SINGLE;
    }
}
