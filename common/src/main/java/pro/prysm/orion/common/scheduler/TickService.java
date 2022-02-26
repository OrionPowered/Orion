package pro.prysm.orion.common.scheduler;

import pro.prysm.orion.common.AbstractOrion;
import pro.prysm.orion.common.event.events.TickEvent;

public class TickService {
    private final TickEvent tickEvent = new TickEvent();

    public TickService() {
        AbstractOrion.getScheduler().scheduleAtFixedRate(() -> AbstractOrion.getEventBus().post(tickEvent), 0, 1); // Tick event every tick
    }
}
