package pro.prysm.orion.server;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import lombok.Getter;
import org.slf4j.LoggerFactory;
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
import pro.prysm.orion.server.net.TCPListener;
import pro.prysm.orion.server.plugin.PluginLoader;
import pro.prysm.orion.server.protocol.Protocol;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Path;

@Getter
public class Orion implements Listener, pro.prysm.orion.api.Orion {

    // STATIC - Logger and EventBus are the only objects that should be here.
    private static final Logger logger = (Logger) LoggerFactory.getLogger("Orion");
    private static final EventBus EVENT_BUS = new pro.prysm.orion.server.event.EventBus();
    private final long startupTime = System.currentTimeMillis();
    // END STATIC
    private final TCPListener listener;
    private final Protocol protocol;
    private final WorldManager worldManager;
    private final CommandHandler commandHandler;
    private final PluginLoader pluginLoader;
    private Config config;

    public Orion() {
        logger.info("Starting Orion...");
        loadConfig();

        Logger root = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.valueOf(config.getString("log-level")));

        worldManager = new WorldManager(config.getString("world"));
        protocol = new Protocol(this, worldManager, config);
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

        try {
            listener.listen(); // Start listening, any code below this will NOT execute (blocking)
        } catch (InterruptedException e) {
            e.printStackTrace();
            // TODO: Shutdown here
        }
    }

    public static void main(String[] args) {
        new Orion(); // Escape Static
    }

    public static Logger getLogger() {
        return logger;
    }

    public static EventBus getEventBus() {
        return EVENT_BUS;
    }

    private void loadConfig() {
        try {
            config = new Config(getClass().getClassLoader(), Path.of("settings.json"), "settings.json");
        } catch (IOException e) {
            logger.warn("Failed to load settings.json!");
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onServerReady(ServerReadyEvent event) {
        long difference = System.currentTimeMillis() - startupTime;
        getLogger().info(String.format("Done (%dms)", difference));
    }

    // All main reload handling should be done from here
    @EventHandler
    public void onReload(ReloadEvent event) {
        loadConfig();
        protocol.reload(config);
    }
}
