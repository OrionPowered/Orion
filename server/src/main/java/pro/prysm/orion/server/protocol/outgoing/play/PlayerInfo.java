package pro.prysm.orion.server.protocol.outgoing.play;

import net.kyori.adventure.text.Component;
import pro.prysm.orion.api.entity.player.GameProfile;
import pro.prysm.orion.api.entity.player.Player;
import pro.prysm.orion.api.entity.player.ProfileProperty;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.PlayerInfoAction;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

import java.util.List;

public class PlayerInfo extends OutgoingPacket {
    private final PlayerInfoAction action;
    private final List<Player> players;

    public PlayerInfo(PlayerInfoAction action, List<Player> players) {
        super(0x36);
        this.action = action;
        this.players = players;
    }

    public PlayerInfo(PlayerInfoAction action, Player player) {
        this(action, List.of(player));
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeVarInt(action.getId());
        buf.writeVarInt(players.size());
        players.parallelStream().forEach(player -> {
            buf.writeUuidIntArray(player.uuid());
            switch (action) {
                case ADD_PLAYER -> {
                    GameProfile profile = player.getProfile();
                    buf.writeString(profile.getUsername());
                    // properties
                    ProfileProperty[] properties = profile.getProperties();
                    buf.writeVarInt(properties.length);
                    for (ProfileProperty property : properties) {
                        buf.writeString(property.name());
                        buf.writeString(property.value());
                        String sig = property.signature();
                        if (sig != null) {
                            buf.writeBoolean(true);
                            buf.writeString(sig);
                        } else buf.writeBoolean(false);
                    }
                    // end properties
                    buf.writeVarInt(player.getGameMode().getId());
                    buf.writeVarInt(player.getLatency());
                    Component displayName = player.getDisplayName();
                    if (displayName != null) {
                        buf.writeBoolean(true);
                        buf.writeComponent(displayName);
                    } else buf.writeBoolean(false);
                }
                case UPDATE_GAMEMODE -> buf.writeVarInt(player.getGameMode().getId());
                case UPDATE_LATENCY -> buf.writeVarInt(player.getLatency());
                case UPDATE_DISPLAY_NAME -> buf.writeComponent(player.getDisplayName());
                // case REMOVE_PLAYER -> {} // No fields
            }
        });

    }
}
