package pro.prysm.orion.server.protocol.handler;

import lombok.Getter;
import pro.prysm.orion.api.data.GameProfile;
import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.entity.player.ImplPlayer;
import pro.prysm.orion.server.entity.player.PlayerFactory;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.incoming.login.EncryptionResponse;
import pro.prysm.orion.server.protocol.incoming.login.LoginStart;
import pro.prysm.orion.server.protocol.outgoing.login.LoginSuccess;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.UUID;

@Getter
public class LoginHandler extends ProtocolHandler {
    private String username;
    private ImplPlayer player;

    public LoginHandler(Connection connection) {
        super(connection);
    }

    @Override
    public void handle(LoginStart packet) {
        username = packet.getUsername();
        if (connection.getProtocol().isOnlineMode())
            connection.sendPacket(connection.getProtocol().newEncryptionRequest());
        else {
            // Offline mode, sends a LoginSuccess packet with a UUID following "OfflinePlayer:<username>"
            GameProfile profile = new GameProfile(username, UUID.nameUUIDFromBytes(String.format("OfflinePlayer:%s", username).getBytes(StandardCharsets.UTF_8)));
            connection.sendPacket(new LoginSuccess(profile.getUniqueId(), username));
            player = PlayerFactory.newPlayer(connection, profile);
            connection.setState(PacketState.PLAY);
            Orion.getLogger().info("{} joining...", username);
        }
    }

    @Override
    public void handle(EncryptionResponse packet) {
        // If server isn't in online mode disconnect the player and stop from handling further
        if (!connection.getProtocol().isOnlineMode()) {
            Orion.getLogger().debug("{} sent an encryption response when no request was sent!", username);
            player.getConnection().disconnect("Invalid encryption packet");
            return;
        }

        byte[] sharedSecret;
        try {
            connection.setVerifyToken(connection.getProtocol().decryptRSA(packet.getVerifyToken()));
            sharedSecret = connection.getProtocol().decryptRSA(packet.getSharedSecret());
            connection.setSharedSecret(sharedSecret);
            connection.enableEncryption(sharedSecret);
            Orion.getLogger().debug("Started encryption for {}", connection.getAddress());
        } catch (GeneralSecurityException e) {
            connection.disconnect("Failed to load encryption.");
            Orion.getLogger().warn("Failed to enable encryption for {}", connection.getAddress());
            e.printStackTrace();
        }

        // If the above executes properly, we can now authenticate the user with the Mojang Session service
        GameProfile profile = connection.getProtocol().join(connection.getProtocol().generateServerId(connection.getSharedSecret()), username);
        if (profile == null) connection.disconnect("Bad login.");
        else {
            connection.sendPacket(new LoginSuccess(profile.getUniqueId(), profile.getUsername()));
            player = PlayerFactory.newPlayer(connection, profile);
            connection.setState(PacketState.PLAY);
            Orion.getLogger().info("{}/{} joining...", profile.getUsername(), profile.getUniqueId());
        }
    }
}
