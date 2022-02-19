package pro.prysm.orion.common.protocol.auth;

import pro.prysm.orion.api.entity.player.GameProfile;

public interface AuthenticationProvider {
    GameProfile join(String serverId, String username);
}
