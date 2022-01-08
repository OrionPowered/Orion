package pro.prysm.orion.server.scheduler;

import java.util.Timer;

// This will probably be implemented much differently later. I needed something for now to start ticking
// - Alex
public class OrionScheduler extends Thread {
    public static final int TPS = 20;
    private final Timer timer;

    public OrionScheduler() {
        this.timer = new Timer();
    }

    private long ticksToMs(long ticks) {
        return (long) (((double) ticks/TPS) * 1000);
    }

    public OrionTask schedule(OrionTask task, long delay) {
        timer.schedule(task, ticksToMs(delay));
        return task;
    }

    public OrionTask schedule(OrionTask task, long delay, long period) {
        timer.schedule(task, ticksToMs(delay), ticksToMs(period));
        return task;
    }

    public OrionTask scheduleAtFixedRate(OrionTask task, long delay, long period) {
        timer.scheduleAtFixedRate(task, ticksToMs(delay), ticksToMs(period));
        return task;
    }
}
