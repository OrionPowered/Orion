package pro.prysm.orion.server.protocol.handler;

import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.entity.ImplPlayer;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.api.data.GameProfile;
import pro.prysm.orion.server.protocol.incoming.login.EncryptionResponse;
import pro.prysm.orion.server.protocol.incoming.login.LoginStart;
import pro.prysm.orion.server.protocol.outgoing.login.LoginSuccess;

import java.security.GeneralSecurityException;
import java.util.UUID;

public class LoginHandler extends ProtocolHandler {
    private String username;
    private ImplPlayer player;
    public LoginHandler(Connection connection) {
        super(connection);
    }

    public ImplPlayer getPlayer() {
        return player;
    }

    @Override
    public void handle(LoginStart packet) {
        username = packet.getUsername();
        if(protocol.isOnlineMode()) connection.sendPacket(protocol.newEncryptionRequest());
        else {
            // Offline mode, sends a LoginSuccess packet with an "empty" uuid
            // TODO: Check if this implementation is correct
            GameProfile profile = new GameProfile(username, UUID.randomUUID());
            connection.sendPacket(new LoginSuccess(profile.getUniqueId(), username));
            player = new ImplPlayer(connection, profile);
            connection.setState(PacketState.PLAY);
            Orion.getLogger().info(String.format("%s joining...", username));
        }
    }

    @Override
    public void handle(EncryptionResponse packet) {
        // If server isn't in online mode disconnect the player and stop from handling further
        if(!protocol.isOnlineMode()) {
            // Finer instead of warning?
            Orion.getLogger().warning(String.format("%s sent an encryption response when no request was sent!", username));
            player.getConnection().disconnect("Invalid encryption packet");
            return;
        }

        byte[] sharedSecret;
        try {
            connection.setVerifyToken(protocol.decryptRSA(packet.getVerifyToken()));
            sharedSecret = protocol.decryptRSA(packet.getSharedSecret());
            connection.setSharedSecret(sharedSecret);
            connection.enableEncryption(sharedSecret);
            Orion.getLogger().finer(String.format("Started encryption for %s", connection.getAddress()));
        } catch (GeneralSecurityException e) {
            connection.disconnect("Failed to load encryption.");
            Orion.getLogger().warning(String.format("Failed to enable encryption for %s", connection.getAddress()));
            e.printStackTrace();
        }

        // If the above executes properly, we can now authenticate the user with the Mojang Session service
        GameProfile profile = protocol.join(protocol.generateServerId(connection.getSharedSecret()), username);
        if (profile == null) connection.disconnect("Bad login.");
        else {
            connection.sendPacket(new LoginSuccess(profile.getUniqueId(), profile.getUsername()));
            player = new ImplPlayer(connection, profile);
            connection.setState(PacketState.PLAY);
            Orion.getLogger().info(String.format("%s/%s joining...", profile.getUsername(), profile.getUniqueId()));
        }
    }
}
