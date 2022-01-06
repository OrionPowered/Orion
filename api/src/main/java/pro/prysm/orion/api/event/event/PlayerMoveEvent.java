package pro.prysm.orion.api.event.event;

import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.entity.Player;
import pro.prysm.orion.api.event.Cancellable;
import pro.prysm.orion.api.event.Event;

public class PlayerMoveEvent extends Event implements Cancellable {
    private final Player player;
    private final Location from;
    private boolean cancelled;
    private Location to;

    public PlayerMoveEvent(Player player, Location to, Location from) {
        this.player = player;
        this.to = to;
        this.from = from;
    }

    public Player getPlayer() {
        return player;
    }

    public Location getTo() {
        return to;
    }

    public void setTo(Location to) {
        this.to = to;
    }

    public Location getFrom() {
        return from;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
