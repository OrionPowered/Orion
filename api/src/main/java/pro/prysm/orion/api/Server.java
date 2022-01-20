package pro.prysm.orion.api;

import pro.prysm.orion.api.entity.Player;
import pro.prysm.orion.api.message.PlaceholderService;

import java.util.UUID;

public interface Server {
    Player getPlayer(UUID uuid);

    PlaceholderService getPlaceholderService();
}
