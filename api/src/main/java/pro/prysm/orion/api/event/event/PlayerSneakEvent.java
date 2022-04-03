package pro.prysm.orion.api.event.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pro.prysm.orion.api.entity.player.Player;
import pro.prysm.orion.api.event.Event;

@Getter
@RequiredArgsConstructor
public class PlayerSneakEvent implements Event {
    private final Player player;
    private final State state = State.SINGLE;
}
