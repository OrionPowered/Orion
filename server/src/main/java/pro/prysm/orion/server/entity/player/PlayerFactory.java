package pro.prysm.orion.server.entity.player;

import pro.prysm.orion.api.entity.player.GameProfile;
import pro.prysm.orion.common.net.Connection;

public class PlayerFactory {
    /**
     * Creates a new {@link ImplPlayer} with the given connection and game profile, the entity id is automatically generated.
     *
     * @param connection The {@link Connection} that will be attached to this player
     * @param profile    The {@link GameProfile} that will be attached to this player
     * @return The created player
     */
    public static ImplPlayer newPlayer(Connection connection, GameProfile profile) {
        return new ImplPlayer(connection, profile, ImplPlayer.useEntityId());
    }

    /**
     * Creates a new {@link ImplPlayer} with the given connection, game profile, and entity id. Be very careful
     * when using this to not provide a duplicate entity id.
     *
     * @param connection The {@link Connection} that will be attached to this player
     * @param profile    The {@link GameProfile} that will be attached to this player
     * @param entityId   The entity id that will be attached to this player
     * @return
     */
    public static ImplPlayer newPlayer(Connection connection, GameProfile profile, int entityId) {
        return new ImplPlayer(connection, profile, entityId);
    }
}
