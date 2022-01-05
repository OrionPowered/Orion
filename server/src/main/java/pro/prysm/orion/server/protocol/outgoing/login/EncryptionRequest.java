package pro.prysm.orion.server.protocol.outgoing.login;

import pro.prysm.orion.server.net.PacketByteBuf;
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
    public void write(PacketByteBuf buf) {
        buf.writeString(""); // Server ID, empty
        buf.writeByteArray(publicKey);
        buf.writeByteArray(verifyToken);
    }
}
