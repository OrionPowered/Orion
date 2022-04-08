package pro.prysm.orion.server.protocol.handler;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import pro.prysm.orion.api.entity.player.GameProfile;
import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.common.OrionExceptionHandler;
import pro.prysm.orion.common.protocol.CipherSuite;
import pro.prysm.orion.common.protocol.Protocol;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.entity.player.ImplPlayer;
import pro.prysm.orion.server.entity.player.PlayerFactory;
import pro.prysm.orion.common.net.Connection;
import pro.prysm.orion.common.protocol.incoming.login.EncryptionResponse;
import pro.prysm.orion.common.protocol.incoming.login.LoginStart;
import pro.prysm.orion.common.protocol.outgoing.login.LoginSuccess;
import java.security.GeneralSecurityException;


@Getter
public class LoginHandler extends AbstractHandler {
    private String username;
    private ImplPlayer player;

    public LoginHandler(Connection connection) {
        super(connection);
    }

    @Override
    public void onDisconnect() {
        Orion.getServer().removePlayer(player);
    }

    @Override
    public void handle(LoginStart packet) {
        username = packet.getUsername();
        Protocol protocol = Orion.getProtocol();
        if (Orion.getServer().isOnlineMode())
            connection.sendPacket(protocol.getCipherSuite().newEncryptionRequest());
        else {
            // Offline mode, send a LoginSuccess packet
            GameProfile profile = protocol.getAuth().join(null, username);
            connection.sendPacket(new LoginSuccess(profile.getUniqueId(), username));
            player = PlayerFactory.newPlayer(connection, profile);
            connection.setState(PacketState.PLAY);
        }
    }

    @Override
    public void handle(EncryptionResponse packet) {
        Protocol protocol = Orion.getProtocol();
        CipherSuite cipher = protocol.getCipherSuite();
        // If server isn't in online mode disconnect the player and stop from handling further
        if (!Orion.getServer().isOnlineMode()) {
            Orion.getLogger().warn("{} sent an encryption response when no request was sent!", username);
            player.getConnection().disconnect(Component.text("Invalid encryption packet"));
            return;
        }

        byte[] sharedSecret;
        try {
            connection.setVerifyToken(cipher.decryptRSA(packet.getVerifyToken()));
            sharedSecret = cipher.decryptRSA(packet.getSharedSecret());
            connection.setSharedSecret(sharedSecret);
            cipher.startEncryption(connection, sharedSecret);
            Orion.getLogger().debug("Started encryption for {}", connection.getAddress());
        } catch (GeneralSecurityException e) {
            OrionExceptionHandler.error("Failed to enable encryption for " +  connection.getAddress(), e);
        }

        // If the above executes properly, we can now authenticate the user with the Mojang Session service
        GameProfile profile = protocol.getAuth().join(cipher.generateServerId(connection.getSharedSecret()), username);
        if (profile == null) connection.disconnect(Component.text("Bad Login."));
        else {
            connection.sendPacket(new LoginSuccess(profile.getUniqueId(), profile.getUsername()));
            player = PlayerFactory.newPlayer(connection, profile);
            connection.setState(PacketState.PLAY);
        }
    }
}
