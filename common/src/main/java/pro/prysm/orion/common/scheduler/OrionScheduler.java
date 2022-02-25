package pro.prysm.orion.common.scheduler;

import pro.prysm.orion.common.OrionThreadFactory;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;

// This will probably be implemented much differently later. I needed something for now to start ticking
// - Alex
public class OrionScheduler {
    public static final int TPS = 20;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1, new OrionThreadFactory("scheduler"));
    private final Map<UUID, ScheduledFuture<?>> map = new ConcurrentHashMap<>();

    private long ticksToMs(long ticks) {
        return (long) (((double) ticks / TPS) * 1000);
    }

    public UUID schedule(Runnable task, long delay) {
        UUID id = UUID.randomUUID();
        map.put(id, scheduler.schedule(task, ticksToMs(delay), TimeUnit.MILLISECONDS));
        return id;
    }

    public UUID schedule(Runnable task, long delay, long period) {
        UUID id = UUID.randomUUID();
        map.put(id, scheduler.scheduleWithFixedDelay(task, ticksToMs(delay), ticksToMs(period), TimeUnit.MILLISECONDS));
        return id;
    }

    public UUID scheduleAtFixedRate(Runnable task, long delay, long period) {
        UUID id = UUID.randomUUID();
        map.put(id, scheduler.scheduleAtFixedRate(task, ticksToMs(delay), ticksToMs(period), TimeUnit.MILLISECONDS));
        return id;
    }

    public void cancel(UUID id) {
        ScheduledFuture<?> removed = map.remove(id);
        if (removed != null) removed.cancel(true);
    }
}
