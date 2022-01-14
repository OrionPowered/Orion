package pro.prysm.orion.api.event.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pro.prysm.orion.api.event.Event;

@AllArgsConstructor
@Getter
public class IncomingPluginMessageEvent implements Event {
    private String channel;
    private byte[] data;

    @Override
    public State getState() {
        return State.SINGLE;
    }
}
