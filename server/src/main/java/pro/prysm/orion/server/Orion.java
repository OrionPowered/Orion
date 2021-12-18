package pro.prysm.orion.server;

import pro.prysm.orion.server.event.EventBus;
import pro.prysm.orion.server.util.Logger;

public class Orion {
    private final Logger logger;
    public static final EventBus EVENT_BUS = new EventBus();

    public Orion() {
        this.logger = new Logger("Orion");
        logger.info("Starting Orion...");
    }

    public Logger getLogger() {
        return logger;
    }

    /**
     * Main Entry Point
     * @param args
     */
    public static void main(String[] args) {
        new Orion();
    }
}
