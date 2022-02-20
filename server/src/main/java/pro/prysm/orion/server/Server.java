package pro.prysm.orion.server;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.slf4j.LoggerFactory;
import pro.prysm.orion.api.entity.player.Player;
import pro.prysm.orion.api.event.EventHandler;
import pro.prysm.orion.api.event.Listener;
import pro.prysm.orion.api.event.event.ReloadEvent;
import pro.prysm.orion.api.event.event.ServerReadyEvent;
import pro.prysm.orion.api.json.Config;
import pro.prysm.orion.api.message.ChatFormatter;
import pro.prysm.orion.api.message.Message;
import pro.prysm.orion.api.message.PlaceholderService;
import pro.prysm.orion.api.net.Connection;
import pro.prysm.orion.api.protocol.status.ServerListResponse;
import pro.prysm.orion.api.util.CollectorUtil;
import pro.prysm.orion.common.command.CommandHandler;
import pro.prysm.orion.common.protocol.Protocol;
import pro.prysm.orion.common.protocol.auth.OfflineAuth;
import pro.prysm.orion.common.protocol.auth.YggdrasilAuth;
import pro.prysm.orion.server.command.HelpCommand;
import pro.prysm.orion.server.command.ReloadCommand;
import pro.prysm.orion.server.command.SendPacketCommand;
import pro.prysm.orion.server.command.UptimeCommand;
import pro.prysm.orion.common.extension.module.ModuleLoader;
import pro.prysm.orion.common.extension.plugin.PluginLoader;
import pro.prysm.orion.server.message.DefaultChatFormatter;
import pro.prysm.orion.common.message.placeholder.UptimePlaceholder;
import pro.prysm.orion.common.net.TCPListener;
import pro.prysm.orion.common.protocol.PlayerInfoAction;
import pro.prysm.orion.common.protocol.outgoing.play.PlayerInfo;
import pro.prysm.orion.common.scheduler.TickService;
import pro.prysm.orion.server.util.ExceptionHandler;
import pro.prysm.orion.server.util.OrionThreadFactory;
import pro.prysm.orion.server.world.DefaultVoidProvider;
import pro.prysm.orion.server.world.LevelProvider;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.file.Path;
import java.util.*;

@Getter
public class Server implements pro.prysm.orion.api.Server, Listener {
    private final TCPListener listener;
    private final CommandHandler commandHandler;
    private final PlaceholderService placeholderService;
    private final List<Player> players;
    private ModuleLoader moduleLoader;
    private PluginLoader pluginLoader;

    @Setter
    private ChatFormatter chatFormatter;

    @Setter
    private LevelProvider levelProvider;

    private Config config;
    private String name;
    private String sessionServer;
    private boolean onlineMode;
    private int maxPlayers;
    private int renderDistance;
    private int simulationDistance;
    private Component motdComponent;

    public Server() {
        loadConfig();

        Logger root = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.valueOf(config.getString("log-level")));

        Orion.getProtocol().setAuth((onlineMode) ? new YggdrasilAuth(sessionServer) : new OfflineAuth());
        loadSLP();

        commandHandler = new CommandHandler();
        players = new ArrayList<>();
        placeholderService = new pro.prysm.orion.common.message.PlaceholderService();
        chatFormatter = new DefaultChatFormatter();

        listener = new TCPListener(
                new InetSocketAddress(config.getString("listener.address"), config.getInt("listener.port")),
                config.getInt("threads")
        );

        Orion.getEventBus().subscribe(this);
        registerCommands();
        registerPlaceholders();

        OrionThreadFactory.singleThread("ExtensionLoader", () -> {
            moduleLoader = new ModuleLoader();
            pluginLoader = new PluginLoader();

            if (levelProvider == null) levelProvider = new DefaultVoidProvider();
            else Orion.getLogger().info("Using {} for level(s)", levelProvider.getClass().getName());
        }).start();

        OrionThreadFactory.singleThread("Listener", () -> {
            try {
                new TickService(); // Start ticking
                new KeepAliveService();
                listener.listen(); // Start listening
            } catch (InterruptedException e) {
                ExceptionHandler.error(e);
            }
        }).start();
    }

    private void loadConfig() {
        try {
            config = new Config(getClass().getClassLoader(), Path.of("settings.json"), "settings.json");
            name = config.getString("server-name");
            sessionServer = config.getStringOrDefault("session-server", "https://sessionserver.mojang.com");
            onlineMode = config.getBoolean("online-mode");
            maxPlayers = config.getInt("max-players");
            renderDistance = config.getInt("world.render-distance");
            simulationDistance = config.getInt("world.simulation-distance");
            motdComponent = new Message(config.getString("motd")).toComponent();
        } catch (IOException e) {
            ExceptionHandler.error("Failed to load settings.json", e);
        }
    }

    private void loadSLP() {
        ServerListResponse slp = Orion.getProtocol().getSlp();
        slp.getVersion().setProtocol(Protocol.PROTOCOL_NUMBER);

        slp.getVersion().setName(name);
        slp.setDescription(motdComponent);
        slp.getPlayers().setMax(maxPlayers);
        // TODO: load from server-icon.png if exists
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("prysm.png")) {
            if (is == null)
                return;
            slp.setFavicon(ServerListResponse.generateFavicon(is));
        } catch (IOException e) {
            ExceptionHandler.error(e);
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

    public void addPlayer(Player player) {
        players.add(player);
        if (!player.isHidden())
            Orion.getProtocol().broadcastPacket(players, new PlayerInfo(PlayerInfoAction.ADD_PLAYER, List.of(player)));
        ((pro.prysm.orion.common.net.Connection) player.getConnection()).sendPacket(new PlayerInfo(PlayerInfoAction.ADD_PLAYER, players.stream().filter(p -> !p.isHidden()).toList()));
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    @Override
    public Optional<Player> getPlayer(UUID uuid) {
        return Optional.of(players.stream().filter(p -> p.uuid().equals(uuid)).collect(CollectorUtil.toSingleton()));
    }

    @Override
    public Optional<Player> getPlayer(Connection connection) {
        return Optional.of(players.stream().filter(p -> p.getConnection().equals(connection)).collect(CollectorUtil.toSingleton()));
    }

    @Override
    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    @Override
    public void broadcast(Identity source, Component message) {
        TextComponent formatted = (TextComponent) Orion.getServer().getChatFormatter().format(source, message);
        logChat(formatted);
        players.parallelStream().forEach(player -> player.sendMessage(source, formatted, MessageType.CHAT));
    }

    @Override
    public void broadcast(Component message) {
        logChat(message);
        players.parallelStream().forEach(player -> player.sendMessage(message));
    }

    public void logChat(Component message) {
        StringBuilder plain = new StringBuilder(((TextComponent) message).content());
        message.children().forEach(c -> plain.append(((TextComponent) c).content()));
        Orion.getLogger().info("[CHAT] {}", plain.toString());
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
        loadSLP();
    }
}