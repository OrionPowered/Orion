package pro.prysm.orion.common;

import ch.qos.logback.classic.Logger;
import pro.prysm.orion.common.event.EventBus;
import pro.prysm.orion.common.scheduler.OrionScheduler;

public abstract class AbstractOrion {
    protected static final long startupTime = System.currentTimeMillis();
    protected static Logger logger;
    protected static EventBus eventBus;
    protected static OrionScheduler scheduler;

    public static Logger getLogger() {
        return logger;
    }

    public static EventBus getEventBus() {
        return eventBus;
    }

    public static OrionScheduler getScheduler() {
        return scheduler;
    }

    public static long getStartupTime() {
        return startupTime;
    }
}
