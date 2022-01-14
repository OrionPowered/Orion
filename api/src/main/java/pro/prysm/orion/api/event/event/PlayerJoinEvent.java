package pro.prysm.orion.api.event.event;

import lombok.Getter;
import pro.prysm.orion.api.entity.Player;
import pro.prysm.orion.api.event.Cancellable;
import pro.prysm.orion.api.event.Event;

@Getter
public class PlayerJoinEvent implements Event, Cancellable {
    private boolean cancelled;
    private Player player;

    public PlayerJoinEvent(Player player) {
        this.player = player;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public State getState() {
        return State.SINGLE;
    }
}
