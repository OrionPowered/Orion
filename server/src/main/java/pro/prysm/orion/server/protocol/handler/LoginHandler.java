package pro.prysm.orion.server.protocol.handler;

import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.incoming.login.EncryptionResponse;
import pro.prysm.orion.server.protocol.incoming.login.LoginStart;

import java.security.GeneralSecurityException;

public class LoginHandler extends ProtocolHandler {
    public LoginHandler(Connection connection) {
        super(connection);
    }

    @Override
    public void handle(EncryptionResponse packet) {
        connection.setVerifyToken(packet.getVerifyToken());
        byte[] sharedSecret;
        try {
            sharedSecret = protocol.decryptRSA(packet.getSharedSecret());
            connection.setSharedSecret(sharedSecret);
            connection.enableEncryption(sharedSecret);
            connection.setState(PacketState.PLAY);
            Orion.getLogger().finer(String.format("Started encryption for %s", connection.getAddress()));
        } catch (GeneralSecurityException e) {
            connection.disconnect("Failed to load encryption.");
            Orion.getLogger().warning(String.format("Failed to enable encryption for %s", connection.getAddress()));
            e.printStackTrace();
        }
        // NEXT: Authentication / Login
    }

    @Override
    public void handle(LoginStart packet) {
        connection.sendPacket(protocol.newEncryptionRequest());
    }
}
