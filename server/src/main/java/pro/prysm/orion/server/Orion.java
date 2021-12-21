package pro.prysm.orion.server;

import pro.prysm.orion.api.JSONConfig;
import pro.prysm.orion.api.event.Listener;
import pro.prysm.orion.api.event.events.IncomingPacketEvent;
import pro.prysm.orion.api.event.events.OutgoingPacketEvent;
import pro.prysm.orion.api.protocol.outgoing.OutgoingPacket;
import pro.prysm.orion.api.protocol.outgoing.status.SLPResponse;
import pro.prysm.orion.server.command.CommandHandler;
import pro.prysm.orion.server.command.commands.HelpCommand;
import pro.prysm.orion.api.event.EventBus;
import pro.prysm.orion.api.event.EventHandler;
import pro.prysm.orion.api.event.events.ServerReadyEvent;
import pro.prysm.orion.server.net.TCPListener;
import pro.prysm.orion.server.plugin.PluginLoader;
import pro.prysm.orion.server.protocol.Protocol;
import pro.prysm.orion.server.util.Logger;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Level;

public class Orion implements Listener, pro.prysm.orion.api.Orion {

    /**
     * Main Entry Point
     * @param args Startup arguments
     */
    public static void main(String[] args) {
        new Orion(); // Escape static
    }

    private final long startupTime = System.currentTimeMillis();
    private JSONConfig config;

    // Logger and EventBus are the only objects that should be static.
    private static final Logger logger = new Logger("Orion", Level.INFO);
//    private static final EventBus eventBus = new EventBus();

    private final TCPListener listener;
    private final Protocol protocol;
    private final CommandHandler commandHandler;
    private final PluginLoader pluginLoader;

    public Orion() {
        logger.info("Starting Orion...");
        loadConfig();

        logger.setLevel(Level.parse(config.getString("log-level")));
        protocol = new Protocol(config);
        commandHandler = new CommandHandler();
        pluginLoader = new PluginLoader();
        commandHandler.registerCommand(new HelpCommand());

        EVENT_BUS.subscribe(this);

        listener = new TCPListener(
                protocol,
                new InetSocketAddress(config.getString("listener.address"), config.getInt("listener.port")),
                config.getInt("threads")
        );

        listener.listen(); // Start listening, any code below this will NOT execute
    }

    private void loadConfig() {
        try {
            config = new JSONConfig(getClass(), new File("settings.json"));
        }
        catch (IOException e) {
            logger.warning("Failed to load settings.json!");
            e.printStackTrace();

        }
    }

    @EventHandler
    public void onServerReady(ServerReadyEvent e) {
        long difference = System.currentTimeMillis() - startupTime;
        getLogger().info(String.format("Done (%dms)", difference));
    }
    @EventHandler
    public void onPacket(OutgoingPacketEvent event, SLPResponse packet) {
        System.out.println(packet.getResponse().toJsonString());
    }

    // ================================================================================================================
    // Getters
    // ================================================================================================================

    public JSONConfig getConfig() {
        return config;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public TCPListener getListener() {
        return listener;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public static Logger getLogger() {
        return logger;
    }

    public PluginLoader getPluginLoader() {
        return pluginLoader;
    }
}
