package pro.prysm.orion.server.scheduler;

import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.event.events.TickEvent;

public class TickService {
    private final TickEvent tickEvent = new TickEvent();

    public TickService() {
        Orion.getScheduler().scheduleAtFixedRate(() -> Orion.getEventBus().post(tickEvent), 0, 1); // Tick event every tick
    }
}
