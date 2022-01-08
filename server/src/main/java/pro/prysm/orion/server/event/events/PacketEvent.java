package pro.prysm.orion.server.event.events;

import lombok.Getter;
import lombok.Setter;
import pro.prysm.orion.api.event.Cancellable;
import pro.prysm.orion.api.event.Event;

@Getter
public abstract class PacketEvent implements Event, Cancellable {
    private State state;
    @Setter
    private boolean cancelled;
}
