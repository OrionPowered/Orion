package pro.prysm.orion.common.protocol.auth;

import pro.prysm.orion.api.entity.player.GameProfile;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class OfflineAuth implements AuthenticationProvider {
    @Override
    public GameProfile join(String serverId, String username) {
        return new GameProfile(username, UUID.nameUUIDFromBytes(String.format("OfflinePlayer:%s", username).getBytes(StandardCharsets.UTF_8)));
    }
}
