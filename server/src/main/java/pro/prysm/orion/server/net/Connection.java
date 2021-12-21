package pro.prysm.orion.server.net;

import io.netty.channel.ChannelHandlerContext;
import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.api.event.events.OutgoingPacketEvent;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;
import pro.prysm.orion.server.protocol.outgoing.login.Disconnect;

import java.net.SocketAddress;

public class Connection implements pro.prysm.orion.api.net.Connection {
    private final ChannelHandlerContext ctx;
    private PacketState state;
    private boolean active;

    public Connection(ChannelHandlerContext ctx) {
        this.ctx = ctx;
        state = PacketState.HANDSHAKE;
        active = true;
    }

    @Override
    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    @Override
    public SocketAddress getAddress() {
        return ctx.channel().remoteAddress();
    }

    public void setState(PacketState state) {
        this.state = state;
    }

    @Override
    public PacketState getState() {
        return state;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    /**
     * Forcibly disconnects the connection and attempts to send a disconnect packet
     * @param reason Reason for disconnect
     */
    @Override
    public void disconnect(String reason) {
        if (active) {
            if (state == PacketState.LOGIN) sendPacket(new Disconnect(null, reason));
            // else if (state == PacketState.PLAY)
            active = false;
            ctx.flush().close();
            Orion.getLogger().finer(String.format("Forcibly closed connection %s", getAddress()));
        }
    }

    @Override
    public void sendPacket(pro.prysm.orion.api.protocol.outgoing.OutgoingPacket packet) {
        pro.prysm.orion.api.Orion.getEventBus().post(new OutgoingPacketEvent(), (OutgoingPacket) packet);
        ctx.writeAndFlush(packet);
    }
}
