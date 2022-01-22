package pro.prysm.orion.api;

import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import pro.prysm.orion.api.entity.Player;
import pro.prysm.orion.api.message.ChatFormatter;
import pro.prysm.orion.api.message.PlaceholderService;
import pro.prysm.orion.api.net.Connection;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Server {
    Optional<Player> getPlayer(UUID uuid);

    Optional<Player> getPlayer(Connection connection);

    List<Player> getPlayers();

    void broadcast(Component message);

    void broadcast(Identity source, Component message);

    PlaceholderService getPlaceholderService();

    ChatFormatter getChatFormatter();

    void setChatFormatter(ChatFormatter formatter);
}
