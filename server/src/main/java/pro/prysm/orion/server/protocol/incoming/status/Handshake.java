package pro.prysm.orion.server.protocol.incoming.status;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;
import pro.prysm.orion.server.protocol.outgoing.status.SLPResponse;

import java.net.InetSocketAddress;

public class Handshake extends IncomingPacket {
    private int protocolVersion;
    private String hostname;
    private short port;
    private PacketState nextState;
    private InetSocketAddress socketAddress;

    public Handshake(Connection connection) {
        super(connection);
        id = 0x00;
        state = PacketState.HANDSHAKE;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public String getHostname() {
        return hostname;
    }

    public short getPort() {
        return port;
    }

    public PacketState getNextState() {
        return nextState;
    }

    @Override
    public void read(ByteBuf buf) {
        protocolVersion = readVarInt(buf);
        hostname = readString(buf);
        port = buf.readShort();
        nextState = (readVarInt(buf) == 2) ? PacketState.LOGIN : PacketState.STATUS;
        connection.setState(nextState);
        if (nextState == PacketState.STATUS) {
            Orion.getLogger().debug(String.format("%s has pinged", connection.getAddress()));
            connection.sendPacket(new SLPResponse());
        }
    }
}
