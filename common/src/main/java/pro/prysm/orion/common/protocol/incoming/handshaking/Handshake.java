package pro.prysm.orion.common.protocol.incoming.handshaking;

import lombok.Getter;
import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.common.net.Connection;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.incoming.IncomingPacket;

@Getter
public class Handshake extends IncomingPacket {
    private int protocolVersion;
    private String hostname;
    private short port;
    private PacketState nextState;

    public Handshake(Connection connection) {
        super(connection);
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
