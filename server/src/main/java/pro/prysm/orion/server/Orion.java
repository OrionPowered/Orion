package pro.prysm.orion.server;

import pro.prysm.orion.api.event.EventHandler;
import pro.prysm.orion.api.event.Listener;
import pro.prysm.orion.api.event.event.ReloadEvent;
import pro.prysm.orion.api.event.event.ServerReadyEvent;
import pro.prysm.orion.api.json.Config;
import pro.prysm.orion.server.command.CommandHandler;
import pro.prysm.orion.server.command.commands.HelpCommand;
import pro.prysm.orion.server.command.commands.ReloadCommand;
import pro.prysm.orion.server.command.commands.SendPacketCommand;
import pro.prysm.orion.server.data.WorldManager;
import pro.prysm.orion.server.event.EventBus;
import pro.prysm.orion.server.event.events.OutgoingPacketEvent;
import pro.prysm.orion.server.net.TCPListener;
import pro.prysm.orion.server.plugin.PluginLoader;
import pro.prysm.orion.server.protocol.Protocol;
import pro.prysm.orion.server.protocol.outgoing.status.SLPResponse;
import pro.prysm.orion.server.util.Logger;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Level;

public class Orion implements Listener, pro.prysm.orion.api.Orion {

    // Logger and EventBus are the only objects that should be static.
    private static final Logger logger = new Logger("Orion", Level.INFO);
    private static final EventBus EVENT_BUS = new pro.prysm.orion.server.event.EventBus();
    private final long startupTime = System.currentTimeMillis();
    private final TCPListener listener;
    private final Protocol protocol;
    private final WorldManager worldManager;
    private final CommandHandler commandHandler;
    private final PluginLoader pluginLoader;
    private Config config;

    public Orion() {
        logger.info("Starting Orion...");
        loadConfig();

        logger.setLevel(Level.parse(config.getString("log-level")));
        worldManager = new WorldManager(config.getString("world"));
        protocol = new Protocol(worldManager, config);
        commandHandler = new CommandHandler();
        pluginLoader = new PluginLoader();

        EVENT_BUS.subscribe(this);

        listener = new TCPListener(
                protocol,
                new InetSocketAddress(config.getString("listener.address"), config.getInt("listener.port")),
                config.getInt("threads")
        );

        commandHandler.registerCommand(new HelpCommand());
        commandHandler.registerCommand(new ReloadCommand());
        commandHandler.registerCommand(new SendPacketCommand(listener.getPipeline().getChannelHandler()));

        listener.listen(); // Start listening, any code below this will NOT execute (blocking)
    }

    /**
     * Main Entry Point
     *
     * @param args Startup arguments
     */
    public static void main(String[] args) {
        new Orion(); // Escape static
    }

    public static Logger getLogger() {
        return logger;
    }

    public static EventBus getEventBus() {
        return EVENT_BUS;
    }

    private void loadConfig() {
        try {
            config = new Config(getClass(), new File("settings.json"));
        } catch (IOException e) {
            logger.warning("Failed to load settings.json!");
            e.printStackTrace();

        }
    }

    @EventHandler
    public void onServerReady(ServerReadyEvent event) {
        long difference = System.currentTimeMillis() - startupTime;
        getLogger().info(String.format("Done (%dms)", difference));
    }

    // ================================================================================================================
    // Getters
    // ================================================================================================================

    // All main reload handling should be done from here
    @EventHandler
    public void onReload(ReloadEvent event) {
        loadConfig();
        protocol.reload(config);
    }

    @EventHandler
    public void onPacket(OutgoingPacketEvent event, SLPResponse packet) {
        // System.out.println(packet.getResponse().toJsonString());
    }

    public Config getConfig() {
        return config;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }

    public TCPListener getListener() {
        return listener;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public PluginLoader getPluginLoader() {
        return pluginLoader;
    }
}
