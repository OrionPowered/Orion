package pro.prysm.orion.server.protocol.incoming.login;

import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

public class EncryptionResponse extends IncomingPacket implements pro.prysm.orion.api.protocol.incoming.login.EncryptionResponse {
    private byte[] sharedSecret;
    private byte[] verifyToken;

    public EncryptionResponse(Connection connection) {
        super(connection);
        id = 0x01;
    }

    public byte[] getSharedSecret() {
        return sharedSecret;
    }

    public byte[] getVerifyToken() {
        return verifyToken;
    }

    @Override
    public void read(PacketByteBuf buf) {
        sharedSecret = buf.readByteArray();
        verifyToken = buf.readByteArray();
        connection.getHandler().handle(this);
    }
}
