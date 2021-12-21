package pro.prysm.orion.api.event;

public interface Cancellable {
    boolean isCancelled();

    void setCancelled(boolean cancel);
}
