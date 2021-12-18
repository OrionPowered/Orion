package pro.prysm.orion.server.event;

public interface Cancellable {
    boolean isCancelled();

    void setCancelled(boolean cancel);
}
