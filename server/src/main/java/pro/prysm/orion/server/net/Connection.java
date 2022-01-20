package pro.prysm.orion.server.net;

import com.velocitypowered.natives.encryption.VelocityCipher;
import com.velocitypowered.natives.encryption.VelocityCipherFactory;
import com.velocitypowered.natives.util.Natives;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.event.events.OutgoingPacketEvent;
import pro.prysm.orion.server.net.pipeline.CipherDecoder;
import pro.prysm.orion.server.net.pipeline.CipherEncoder;
import pro.prysm.orion.server.protocol.Protocol;
import pro.prysm.orion.server.protocol.handler.HandshakeHandler;
import pro.prysm.orion.server.protocol.handler.LoginHandler;
import pro.prysm.orion.server.protocol.handler.ProtocolHandler;
import pro.prysm.orion.server.protocol.handler.StatusHandler;
import pro.prysm.orion.server.protocol.handler.play.PlayHandler;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;
import pro.prysm.orion.server.protocol.outgoing.login.LoginDisconnected;
import pro.prysm.orion.server.protocol.outgoing.play.PlayDisconnect;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.SocketAddress;
import java.security.GeneralSecurityException;

@Getter
@RequiredArgsConstructor
public class Connection implements pro.prysm.orion.api.net.Connection {
    private final ChannelHandlerContext ctx;
    @Setter
    private byte[] sharedSecret;
    @Setter
    private byte[] verifyToken;

    // We start off with a Handshake Handler since the first connection will be in the Handshake state.
    private ProtocolHandler handler = new HandshakeHandler(this);
    private PacketState state = PacketState.HANDSHAKE;
    @Setter
    private boolean active = true;

    public SocketAddress getAddress() {
        return ctx.channel().remoteAddress();
    }

    public void setState(PacketState state) {
        this.state = state;
        switch (state) {
            case STATUS -> handler = new StatusHandler(this);
            case LOGIN -> handler = new LoginHandler(this);
            case PLAY -> handler = new PlayHandler(((LoginHandler) handler).getPlayer());
        }
    }

    public void enableEncryption(byte[] secret) throws GeneralSecurityException {
        if (!active || secret == null) return;
        SecretKey key = new SecretKeySpec(secret, "AES");
        VelocityCipherFactory factory = Natives.cipher.get();
        VelocityCipher decryptionCipher = factory.forDecryption(key);
        VelocityCipher encryptionCipher = factory.forEncryption(key);
        ctx.channel().pipeline().addBefore(Protocol.LENGTH_DECODER, Protocol.CIPHER_DECODER, new CipherDecoder(decryptionCipher));
        ctx.channel().pipeline().addBefore(Protocol.LENGTH_ENCODER, Protocol.CIPHER_ENCODER, new CipherEncoder(encryptionCipher));
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
            Orion.getLogger().debug("Forcibly closed connection {}", getAddress());
            handler.onDisconnect();
        }
    }

    public void sendPacket(OutgoingPacket packet) {
        OutgoingPacketEvent event = new OutgoingPacketEvent();
        Orion.getEventBus().post(event, packet);
        if (!event.isCancelled()) ctx.writeAndFlush(packet);
    }
}
