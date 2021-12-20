package pro.prysm.orion.server;

import pro.prysm.orion.api.chat.Chat;
import pro.prysm.orion.api.protocol.ServerListResponse;
import pro.prysm.orion.server.command.CommandHandler;
import pro.prysm.orion.server.command.commands.HelpCommand;
import pro.prysm.orion.server.event.EventBus;
import pro.prysm.orion.server.event.EventHandler;
import pro.prysm.orion.server.event.events.OutgoingPacketEvent;
import pro.prysm.orion.server.event.events.ServerReadyEvent;
import pro.prysm.orion.server.net.TCPListener;
import pro.prysm.orion.server.protocol.PacketRegistry;
import pro.prysm.orion.server.protocol.outgoing.status.SLPResponse;
import pro.prysm.orion.server.util.Logger;

import java.net.InetSocketAddress;
import java.util.logging.Level;

public class Orion implements pro.prysm.orion.server.event.Listener {
    private final long startupTime = System.currentTimeMillis();
    private static final Logger logger = new Logger("Orion", Level.FINER);
    public static final EventBus EVENT_BUS = new EventBus();
    private final TCPListener TCPListener;
    private final CommandHandler commandHandler;

    public Orion() {
        logger.info("Starting Orion...");
        EVENT_BUS.subscribe(this);
        commandHandler = new CommandHandler();
        commandHandler.registerCommand(new HelpCommand());

        // Temporary for now
        ServerListResponse defaultSLPResponse = new ServerListResponse();
        defaultSLPResponse.setProtocolVersion(757);
        defaultSLPResponse.setServerName("Orion");
        defaultSLPResponse.setMaxPlayers(20);
        defaultSLPResponse.setOnlinePlayers(0);
        defaultSLPResponse.setDescription(Chat.miniMessage().parse("<color:#2fc1fa>Orion Server Software</color>"));
        PacketRegistry.defaultSLPResponse = defaultSLPResponse;

        TCPListener = new TCPListener(this, new InetSocketAddress("127.0.0.1", 25565));
    }

    @EventHandler
    public void onServerReady(ServerReadyEvent e) {
        long difference = System.currentTimeMillis() - startupTime;
        getLogger().info(String.format("Done (%dms)", difference));
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

    public TCPListener getTCPListener() {
        return TCPListener;
    }
}
