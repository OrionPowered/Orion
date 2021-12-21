package pro.prysm.orion.server.net;

import io.netty.channel.ChannelHandlerContext;
import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.event.events.OutgoingPacketEvent;
import pro.prysm.orion.server.protocol.Protocol;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;
import pro.prysm.orion.server.protocol.outgoing.login.Disconnect;

import java.net.SocketAddress;

public class Connection {
    private final ChannelHandlerContext ctx;
    private PacketState state;
    private boolean active;

    public Connection(ChannelHandlerContext ctx) {
        this.ctx = ctx;
        state = PacketState.HANDSHAKE;
        active = true;
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

    public boolean isActive() {
        return active;
    }

    /**
     * Forcibly disconnects the connection and attempts to send a disconnect packet
     * @param reason Reason for disconnect
     */
    public void disconnect(String reason) {
        if (active) {
            if (state == PacketState.LOGIN) sendPacket(new Disconnect(null, reason));
            // else if (state == PacketState.PLAY)
            active = false;
            ctx.flush().close();
            Orion.getLogger().finer(String.format("Forcibly closed connection %s", getAddress()));
        }
    }

    public void sendPacket(OutgoingPacket packet) {
        Orion.getEventBus().post(new OutgoingPacketEvent(), packet);
        ctx.writeAndFlush(packet);
    }
}
