package pro.prysm.orion.server;

import pro.prysm.orion.server.command.CommandHandler;
import pro.prysm.orion.server.command.commands.HelpCommand;
import pro.prysm.orion.server.event.EventBus;
import pro.prysm.orion.server.event.EventHandler;
import pro.prysm.orion.server.event.events.IncomingPacketEvent;
import pro.prysm.orion.server.event.events.TestEvent;
import pro.prysm.orion.server.net.TCPListener;
import pro.prysm.orion.server.protocol.incoming.status.Handshake;
import pro.prysm.orion.server.util.Logger;

public class Orion implements pro.prysm.orion.server.event.Listener {
    private static final Logger logger = new Logger("Orion");
    public static final EventBus EVENT_BUS = new EventBus();
    private final TCPListener TCPListener;
    private final CommandHandler commandHandler;

    public Orion() {
        logger.info("Starting Orion...");
        EVENT_BUS.subscribe(this);
        EVENT_BUS.post(new TestEvent());
        commandHandler = new CommandHandler();
        commandHandler.registerCommand(new HelpCommand());
        TCPListener = new TCPListener(this);
    }

    @EventHandler
    public void onTestEvent(TestEvent e) {
        System.out.println("TEST EVENT");
    }

    @EventHandler
    public void onHandshake(IncomingPacketEvent e, Handshake packet) {
        System.out.println("Handshake packet!");
    }

    public static Logger getLogger() {
        return logger;
    }

    /**
     * Main Entry Point
     * @param args
     */
    public static void main(String[] args) {
        new Orion();
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public pro.prysm.orion.server.net.TCPListener getTCPListener() {
        return TCPListener;
    }
}
