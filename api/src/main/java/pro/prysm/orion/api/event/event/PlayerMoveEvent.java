package pro.prysm.orion.api.event.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.entity.Player;
import pro.prysm.orion.api.event.Cancellable;
import pro.prysm.orion.api.event.Event;

@Getter
@Setter
public class PlayerMoveEvent implements Event, Cancellable {
    private final Player player;
    private final Location from;
    private boolean cancelled;
    private Location to;

    public PlayerMoveEvent(Player player, Location from, Location to) {
        this.player = player;
        this.from = from;
        this.to = to;
    }

    @Override
    public State getState() {
        return State.Single;
    }
}
