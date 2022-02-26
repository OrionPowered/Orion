package pro.prysm.orion.common;

import ch.qos.logback.classic.Logger;
import pro.prysm.orion.api.Server;
import pro.prysm.orion.common.event.EventBus;
import pro.prysm.orion.common.protocol.Protocol;
import pro.prysm.orion.common.scheduler.OrionScheduler;

public abstract class AbstractOrion {
    protected static final long startupTime = System.currentTimeMillis();
    protected static Logger logger;
    protected static Protocol protocol;
    protected static EventBus eventBus;
    protected static OrionScheduler scheduler;
    protected static Server IServer;

    public static Logger getLogger() {
        return logger;
    }

    public static Protocol getProtocol() {
        return protocol;
    }

    public static EventBus getEventBus() {
        return eventBus;
    }

    public static OrionScheduler getScheduler() {
        return scheduler;
    }

    public static Server getIServer() {
        return IServer;
    }

    public static long getStartupTime() {
        return startupTime;
    }
}
