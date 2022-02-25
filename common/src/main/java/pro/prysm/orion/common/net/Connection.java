package pro.prysm.orion.common.net;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.common.AbstractOrion;
import pro.prysm.orion.common.event.events.OutgoingPacketEvent;
import pro.prysm.orion.common.protocol.Handler;
import pro.prysm.orion.common.protocol.outgoing.OutgoingPacket;
import pro.prysm.orion.common.protocol.outgoing.login.LoginDisconnected;
import pro.prysm.orion.common.protocol.outgoing.play.PlayDisconnect;

import java.net.SocketAddress;

@Getter
@Setter
@EqualsAndHashCode
public class Connection implements pro.prysm.orion.api.net.Connection {
    private final ChannelHandlerContext ctx;
    private PacketState state = PacketState.HANDSHAKE;
    private Handler handler;
    private byte[] sharedSecret;
    private byte[] verifyToken;
    private boolean active = true;

    public Connection(ChannelHandlerContext ctx) {
        this.ctx = ctx;
        this.handler = AbstractOrion.getProtocol().getDefaultHandler(this);
    }

    public SocketAddress getAddress() {
        return ctx.channel().remoteAddress();
    }

    public void setState(PacketState state) {
        this.state = state;
        handler = handler.getForState(state);
    }

    /**
     * Forcibly disconnects the connection and attempts to send a disconnect packet
     *
     * @param reason Reason for disconnect
     */
    public void disconnect(Component reason) {
        if (active) {
            if (state == PacketState.LOGIN) sendPacket(new LoginDisconnected(reason));
            else if (state == PacketState.PLAY) sendPacket(new PlayDisconnect(reason));
            active = false;
            ctx.flush().close().addListener(ChannelFutureListener.CLOSE);
            AbstractOrion.getLogger().debug("Forcibly closed connection {}", getAddress());
            handler.onDisconnect();
        }
    }

    public void sendPacket(OutgoingPacket packet) {
        OutgoingPacketEvent event = new OutgoingPacketEvent();
        AbstractOrion.getEventBus().post(event, packet);
        if (!event.isCancelled()) ctx.writeAndFlush(packet);
    }
}
