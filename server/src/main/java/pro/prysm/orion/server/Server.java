package pro.prysm.orion.server;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import lombok.Getter;
import org.slf4j.LoggerFactory;
import pro.prysm.orion.api.entity.Player;
import pro.prysm.orion.api.event.EventHandler;
import pro.prysm.orion.api.event.Listener;
import pro.prysm.orion.api.event.event.ReloadEvent;
import pro.prysm.orion.api.event.event.ServerReadyEvent;
import pro.prysm.orion.api.json.Config;
import pro.prysm.orion.api.message.ChatFormatter;
import pro.prysm.orion.api.message.PlaceholderService;
import pro.prysm.orion.server.command.CommandHandler;
import pro.prysm.orion.server.command.commands.HelpCommand;
import pro.prysm.orion.server.command.commands.ReloadCommand;
import pro.prysm.orion.server.command.commands.SendPacketCommand;
import pro.prysm.orion.server.command.commands.UptimeCommand;
import pro.prysm.orion.server.message.DefaultChatFormatter;
import pro.prysm.orion.server.message.placeholder.UptimePlaceholder;
import pro.prysm.orion.server.module.ModuleLoader;
import pro.prysm.orion.server.net.TCPListener;
import pro.prysm.orion.server.plugin.PluginLoader;
import pro.prysm.orion.server.protocol.Protocol;
import pro.prysm.orion.server.scheduler.TickService;
import pro.prysm.orion.server.util.ExceptionHandler;
import pro.prysm.orion.server.world.LevelManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
public class Server implements pro.prysm.orion.api.Server, Listener {
    private final TCPListener listener;
    private final LevelManager levelManager;
    private final Protocol protocol;
    private final CommandHandler commandHandler;
    private final ModuleLoader moduleLoader;
    private final PluginLoader pluginLoader;
    private final HashMap<UUID, Player> players;
    private final PlaceholderService placeholderService;

    @Setter
    private ChatFormatter chatFormatter;

    public Server() {
        loadConfig();

        Logger root = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.valueOf(config.getString("log-level")));

        // If world.void is true, we create a level manager with no specified world.
        levelManager = (config.getBoolean("world.void")) ? new LevelManager() : new LevelManager(config.getString("world.name"));

        protocol = new Protocol(this);
        commandHandler = new CommandHandler();
        moduleLoader = new ModuleLoader();
        pluginLoader = new PluginLoader();
        players = new HashMap<>();
        placeholderService = new pro.prysm.orion.server.message.PlaceholderService();
        chatFormatter = new DefaultChatFormatter();

        listener = new TCPListener(
                protocol,
                new InetSocketAddress(config.getString("listener.address"), config.getInt("listener.port")),
                config.getInt("threads")
        );

        Orion.getEventBus().subscribe(this);
        registerCommands();
        registerPlaceholders();

        CompletableFuture.runAsync(() -> { // Prevent listening from blocking the thread
            try {
                new TickService(); // Start ticking
                listener.listen(); // Start listening
            } catch (InterruptedException e) {
                ExceptionHandler.error(e);
            }
        });
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

    @Override
    public Player getPlayer(UUID uuid) {
        return players.get(uuid);
    }

    @EventHandler
    public void onServerReady(ServerReadyEvent event) {
        long difference = System.currentTimeMillis() - Orion.getStartupTime();
        Orion.getLogger().info("Done ({}ms)", difference);
    }

    // All main reload handling should be done from here
    @EventHandler
    public void onReload(ReloadEvent event) {
        loadConfig();
        protocol.reload(config);
    }
}
