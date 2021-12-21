package pro.prysm.orion.server.protocol.incoming.login;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.Protocol;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;
import pro.prysm.orion.server.protocol.outgoing.login.LoginSuccess;

import java.security.GeneralSecurityException;
import java.util.UUID;

public class EncryptionResponse extends IncomingPacket implements pro.prysm.orion.api.protocol.incoming.login.EncryptionResponse {

    public EncryptionResponse(Protocol protocol, Connection connection) {
        super(protocol, connection);
        id = 0x01;
    }

    @Override
    public void read(ByteBuf buf) {
        byte[] sharedSecret = readByteArray(buf);
        connection.setVerifyToken(readByteArray(buf));
        try {
            sharedSecret = protocol.decryptRSA(sharedSecret);
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
}
