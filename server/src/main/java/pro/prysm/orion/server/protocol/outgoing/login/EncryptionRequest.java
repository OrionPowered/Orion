package pro.prysm.orion.server.protocol.outgoing.login;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

public class EncryptionRequest extends OutgoingPacket implements pro.prysm.orion.api.protocol.outgoing.login.EncryptionRequest {
    private final byte[] publicKey;
    private final byte[] verifyToken;

    public EncryptionRequest(byte[] publicKey, byte[] verifyToken) {
        id = 0x01;
        this.publicKey = publicKey;
        this.verifyToken = verifyToken;
    }

    @Override
    public void write(ByteBuf buf) {
        writeString("", buf); // Server ID, empty
        writeByteArray(publicKey, buf);
        writeByteArray(verifyToken, buf);
    }
}
