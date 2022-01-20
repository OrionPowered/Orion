package pro.prysm.orion.server;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import pro.prysm.orion.api.event.Listener;
import pro.prysm.orion.server.event.EventBus;
import pro.prysm.orion.server.scheduler.OrionScheduler;

public class Orion implements Listener, pro.prysm.orion.api.Orion {
    private static final long startupTime = System.currentTimeMillis();
    private static Logger logger;
    private static OrionScheduler scheduler;
    private static EventBus eventBus;
    private static Server server;

    public static void main(String[] args) {
        logger = (Logger) LoggerFactory.getLogger("Orion");
        logger.info("Starting Orion...");
        scheduler = new OrionScheduler();
        eventBus = new EventBus();
        server = new Server();
    }

    public static Server getServer() {
        return server;
    }

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
