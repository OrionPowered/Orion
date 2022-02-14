package pro.prysm.orion.api.event.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pro.prysm.orion.api.event.Event;
import pro.prysm.orion.api.net.PayloadBuffer;

@AllArgsConstructor
@Getter
public class IncomingPluginMessageEvent implements Event {
    private String channel;
    private PayloadBuffer payload;

    @Override
    public State getState() {
        return State.SINGLE;
    }
}
