package pro.prysm.orion.server.net;

import io.netty.channel.ChannelHandlerContext;
import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.event.events.OutgoingPacketEvent;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

import java.net.SocketAddress;

public class Connection {
    private final ChannelHandlerContext ctx;
    private PacketState state;

    public Connection(ChannelHandlerContext ctx) {
        this.ctx = ctx;
        state = PacketState.HANDSHAKE;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public SocketAddress getAddress() {
        return ctx.channel().remoteAddress();
    }

    public void setState(PacketState state) {
        this.state = state;
    }

    public PacketState getState() {
        return state;
    }

    public void sendPacket(OutgoingPacket packet) {
        Orion.EVENT_BUS.post(new OutgoingPacketEvent(), packet);
        ctx.writeAndFlush(packet);
    }
}
