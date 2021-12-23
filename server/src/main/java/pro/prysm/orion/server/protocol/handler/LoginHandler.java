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
        connection.sendPacket(protocol.newEncryptionRequest());
    }

    @Override
    public void handle(EncryptionResponse packet) {
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
