package pro.prysm.orion.common.protocol.outgoing.login;

import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.outgoing.OutgoingPacket;

public class EncryptionRequest extends OutgoingPacket {
    private final byte[] publicKey;
    private final byte[] verifyToken;

    public EncryptionRequest(byte[] publicKey, byte[] verifyToken) {
        super(0x01);
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
