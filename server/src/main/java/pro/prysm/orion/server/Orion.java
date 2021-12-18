package pro.prysm.orion.server;

import pro.prysm.orion.server.event.EventBus;
import pro.prysm.orion.server.event.EventHandler;
import pro.prysm.orion.server.event.events.IncomingPacketEvent;
import pro.prysm.orion.server.event.events.TestEvent;
import pro.prysm.orion.server.net.TCPListener;
import pro.prysm.orion.server.protocol.incoming.Handshake;
import pro.prysm.orion.server.util.Logger;

public class Orion implements pro.prysm.orion.server.event.Listener {
    private final Logger logger;
    public static final EventBus EVENT_BUS = new EventBus();
    private final TCPListener TCPListener;

    public Orion() {
        this.logger = new Logger("Orion");
        logger.info("Starting Orion...");
        TCPListener = new TCPListener();
        EVENT_BUS.subscribe(this);
        EVENT_BUS.post(new TestEvent());
    }

    @EventHandler
    public void onTestEvent(TestEvent e) {
        System.out.println("TEST EVENT");
    }

    @EventHandler
    public void onHandshake(IncomingPacketEvent e, Handshake packet) {
        System.out.println("Handshake packet!");
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
