package pro.prysm.orion.api.event;

public interface EventBus {
    void subscribe(Listener listener);

    void unSubscribe(Listener listener);

    void post(Event event);
}
