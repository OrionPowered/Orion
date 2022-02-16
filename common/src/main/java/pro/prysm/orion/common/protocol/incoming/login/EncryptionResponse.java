package pro.prysm.orion.common.protocol.incoming.login;

import lombok.Getter;
import pro.prysm.orion.common.net.Connection;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.incoming.IncomingPacket;

@Getter
public class EncryptionResponse extends IncomingPacket {
    private byte[] sharedSecret;
    private byte[] verifyToken;

    public EncryptionResponse(Connection connection) {
        super(connection);
    }

    @Override
    public void read(PacketByteBuf buf) {
        sharedSecret = buf.readByteArray();
        verifyToken = buf.readByteArray();
        connection.getHandler().handle(this);
    }
}
