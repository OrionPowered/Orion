package pro.prysm.orion.server;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import pro.prysm.orion.common.AbstractOrion;
import pro.prysm.orion.common.event.EventBus;
import pro.prysm.orion.common.scheduler.OrionScheduler;

public class Orion extends AbstractOrion {
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

    public static long getStartupTime() {
        return startupTime;
    }
}
