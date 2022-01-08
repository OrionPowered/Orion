package pro.prysm.orion.api.event;

public interface Event {
    State getState();

    enum State {
        SINGLE,
        PRE,
        POST
    }
}
