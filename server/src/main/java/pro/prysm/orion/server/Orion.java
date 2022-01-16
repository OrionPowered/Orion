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
import pro.prysm.orion.api.message.PlaceholderService;
import pro.prysm.orion.server.command.CommandHandler;
import pro.prysm.orion.server.command.commands.HelpCommand;
import pro.prysm.orion.server.command.commands.ReloadCommand;
import pro.prysm.orion.server.command.commands.SendPacketCommand;
import pro.prysm.orion.server.command.commands.UptimeCommand;
import pro.prysm.orion.server.event.EventBus;
import pro.prysm.orion.server.message.placeholder.UptimePlaceholder;
import pro.prysm.orion.server.module.ModuleLoader;
import pro.prysm.orion.server.net.TCPListener;
import pro.prysm.orion.server.plugin.PluginLoader;
import pro.prysm.orion.server.protocol.Protocol;
import pro.prysm.orion.server.scheduler.OrionScheduler;
import pro.prysm.orion.server.scheduler.TickService;
import pro.prysm.orion.server.util.ExceptionHandler;
import pro.prysm.orion.server.world.LevelManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Path;

@Getter
public class Orion implements Listener, pro.prysm.orion.api.Orion {
    // STATIC
    private static final Logger logger = (Logger) LoggerFactory.getLogger("Orion");
    private static final EventBus EVENT_BUS = new pro.prysm.orion.server.event.EventBus();
    private static final OrionScheduler SCHEDULER = new OrionScheduler();
    private static final PlaceholderService placeholderService = new pro.prysm.orion.server.message.PlaceholderService();
    private static final long startupTime = System.currentTimeMillis();

    // END STATIC

    private final TCPListener listener;
    private final Protocol protocol;
    private final LevelManager levelManager;
    private final CommandHandler commandHandler;
    private final ModuleLoader moduleLoader;
    private final PluginLoader pluginLoader;
    private Config config;


    public Orion() {
        logger.info("Starting Orion...");
        Thread.currentThread().setUncaughtExceptionHandler(ExceptionHandler.threadExceptionHandler);
        loadConfig();

        Logger root = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.valueOf(config.getString("log-level")));

        levelManager = new LevelManager(config.getString("world"));
        protocol = new Protocol(this, levelManager, config);
        commandHandler = new CommandHandler();
        moduleLoader = new ModuleLoader();
        pluginLoader = new PluginLoader();

        EVENT_BUS.subscribe(this);

        listener = new TCPListener(
                protocol,
                new InetSocketAddress(config.getString("listener.address"), config.getInt("listener.port")),
                config.getInt("threads")
        );

        registerCommands();
        registerPlaceholders();

        try {
            new TickService(); // Start ticking
            listener.listen(); // Start listening, any code below this will NOT execute (blocking)
        } catch (InterruptedException e) {
            ExceptionHandler.error(e);
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

    public static OrionScheduler getScheduler() {
        return SCHEDULER;
    }

    public static PlaceholderService getPlaceholderService() {
        return placeholderService;
    }

    public static long getStartupTime() {
        return startupTime;
    }

    private void loadConfig() {
        try {
            config = new Config(getClass().getClassLoader(), Path.of("settings.json"), "settings.json");
        } catch (IOException e) {
            ExceptionHandler.error("Failed to load settings.json", e);
        }
    }

    private void registerCommands() {
        commandHandler.registerCommand(new HelpCommand());
        commandHandler.registerCommand(new ReloadCommand());
        commandHandler.registerCommand(new UptimeCommand());
        commandHandler.registerCommand(new SendPacketCommand(listener.getPipeline().getChannelHandler()));
    }

    private void registerPlaceholders() {
        placeholderService.register("uptime", new UptimePlaceholder());
    }

    @EventHandler
    public void onServerReady(ServerReadyEvent event) {
        long difference = System.currentTimeMillis() - startupTime;
        getLogger().info("Done ({}ms)", difference);
    }

    // All main reload handling should be done from here
    @EventHandler
    public void onReload(ReloadEvent event) {
        loadConfig();
        protocol.reload(config);
    }
}
