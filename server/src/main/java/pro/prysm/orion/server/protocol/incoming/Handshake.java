package pro.prysm.orion.server.protocol.incoming;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.api.protocol.HandshakeState;
import pro.prysm.orion.api.protocol.PacketState;

public class Handshake extends IncomingPacket {
    private int protocolVersion;
    private String address;
    private short port;
    private HandshakeState nextState;

    public Handshake() {
        id = 0x00;
        state = PacketState.HANDSHAKE;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public String getAddress() {
        return address;
    }

    public short getPort() {
        return port;
    }

    public HandshakeState getNextState() {
        return nextState;
    }

    @Override
    public void read(ByteBuf buf) {
        protocolVersion = readVarInt(buf);
        address = readString(buf);
        port = buf.readShort();
        nextState = (readVarInt(buf) == 2) ? HandshakeState.LOGIN : HandshakeState.STATUS;
    }
}
