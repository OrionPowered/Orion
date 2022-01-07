package pro.prysm.orion.server.protocol.incoming.handshaking;

import lombok.Getter;
import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.api.protocol.incoming.status.HandshakePacket;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

@Getter
public class Handshake extends IncomingPacket implements HandshakePacket {
    private int protocolVersion;
    private String hostname;
    private short port;
    private PacketState nextState;

    public Handshake(Connection connection) {
        super(0x00, connection);
        state = PacketState.HANDSHAKE;
    }

    @Override
    public void read(PacketByteBuf buf) {
        protocolVersion = buf.readVarInt();
        hostname = buf.readString();
        port = buf.readShort();
        nextState = (buf.readVarInt() == 2) ? PacketState.LOGIN : PacketState.STATUS;
        connection.getHandler().handle(this);
    }
}
