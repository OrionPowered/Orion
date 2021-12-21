package pro.prysm.orion.server.protocol.incoming.status;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

public class Handshake extends IncomingPacket implements pro.prysm.orion.api.protocol.incoming.status.Handshake {
    private int protocolVersion;
    private String hostname;
    private short port;
    private PacketState nextState;

    public Handshake(Connection connection) {
        super(connection);
        id = 0x00;
        state = PacketState.HANDSHAKE;
    }

    @Override
    public int getProtocolVersion() {
        return protocolVersion;
    }

    @Override
    public String getHostname() {
        return hostname;
    }

    @Override
    public short getPort() {
        return port;
    }

    @Override
    public PacketState getNextState() {
        return nextState;
    }

    @Override
    public void read(ByteBuf buf) {
        protocolVersion = readVarInt(buf);
        hostname = readString(buf);
        port = buf.readShort();
        nextState = (readVarInt(buf) == 2) ? PacketState.LOGIN : PacketState.STATUS;
        connection.getHandler().handle(this);
    }
}
