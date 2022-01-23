package pro.prysm.orion.server.protocol.handler;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import pro.prysm.orion.api.data.GameProfile;
import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.entity.player.ImplPlayer;
import pro.prysm.orion.server.entity.player.PlayerFactory;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.Protocol;
import pro.prysm.orion.server.protocol.incoming.login.EncryptionResponse;
import pro.prysm.orion.server.protocol.incoming.login.LoginStart;
import pro.prysm.orion.server.protocol.outgoing.login.LoginSuccess;
import pro.prysm.orion.server.util.ExceptionHandler;

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
        Protocol protocol = Orion.getServer().getProtocol();
        if (Orion.getServer().isOnlineMode())
            connection.sendPacket(protocol.newEncryptionRequest());
        else {
            // Offline mode, sends a LoginSuccess packet with a UUID following "OfflinePlayer:<username>"
            GameProfile profile = new GameProfile(username, UUID.nameUUIDFromBytes(String.format("OfflinePlayer:%s", username).getBytes(StandardCharsets.UTF_8)));
            connection.sendPacket(new LoginSuccess(profile.getUniqueId(), username));
            player = PlayerFactory.newPlayer(connection, profile);
            connection.setState(PacketState.PLAY);
        }
    }

    @Override
    public void handle(EncryptionResponse packet) {
        Protocol protocol = Orion.getServer().getProtocol();
        // If server isn't in online mode disconnect the player and stop from handling further
        if (!Orion.getServer().isOnlineMode()) {
            Orion.getLogger().warn("{} sent an encryption response when no request was sent!", username);
            player.getConnection().disconnect(Component.text("Invalid encryption packet"));
            return;
        }

        byte[] sharedSecret;
        try {
            connection.setVerifyToken(protocol.decryptRSA(packet.getVerifyToken()));
            sharedSecret = protocol.decryptRSA(packet.getSharedSecret());
            connection.setSharedSecret(sharedSecret);
            connection.enableEncryption(sharedSecret);
            Orion.getLogger().debug("Started encryption for {}", connection.getAddress());
        } catch (GeneralSecurityException e) {
            ExceptionHandler.error("Failed to enable encryption for " +  connection.getAddress(), e);
        }

        // If the above executes properly, we can now authenticate the user with the Mojang Session service
        GameProfile profile = protocol.join(protocol.generateServerId(connection.getSharedSecret()), username);
        if (profile == null) connection.disconnect(Component.text("Bad Login."));
        else {
            connection.sendPacket(new LoginSuccess(profile.getUniqueId(), profile.getUsername()));
            player = PlayerFactory.newPlayer(connection, profile);
            connection.setState(PacketState.PLAY);
        }
    }
}
