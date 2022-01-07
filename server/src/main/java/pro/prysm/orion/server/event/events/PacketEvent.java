package pro.prysm.orion.server.event.events;

import lombok.Getter;
import lombok.Setter;
import pro.prysm.orion.api.event.Cancellable;
import pro.prysm.orion.api.event.Event;

@Getter
@Setter
public abstract class PacketEvent extends Event implements Cancellable {
    private boolean cancelled;
}
