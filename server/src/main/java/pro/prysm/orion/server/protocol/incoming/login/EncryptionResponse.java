package pro.prysm.orion.server.protocol.incoming.login;

import lombok.Getter;
import pro.prysm.orion.api.protocol.incoming.login.EncryptionResponsePacket;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

@Getter
public class EncryptionResponse extends IncomingPacket implements EncryptionResponsePacket {
    private byte[] sharedSecret;
    private byte[] verifyToken;

    public EncryptionResponse(Connection connection) {
        super(0x01, connection);
    }

    @Override
    public void read(PacketByteBuf buf) {
        sharedSecret = buf.readByteArray();
        verifyToken = buf.readByteArray();
        connection.getHandler().handle(this);
    }
}
