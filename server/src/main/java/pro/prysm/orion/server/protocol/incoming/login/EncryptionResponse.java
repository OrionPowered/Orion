package pro.prysm.orion.server.protocol.incoming.login;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.server.net.Connection;
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
    public void read(ByteBuf buf) {
        sharedSecret = readByteArray(buf);
        verifyToken = readByteArray(buf);
        connection.getHandler().handle(this);
    }
}
