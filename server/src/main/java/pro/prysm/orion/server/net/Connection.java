package pro.prysm.orion.server.net;

import com.velocitypowered.natives.encryption.VelocityCipher;
import com.velocitypowered.natives.encryption.VelocityCipherFactory;
import com.velocitypowered.natives.util.Natives;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import pro.prysm.orion.api.event.events.OutgoingPacketEvent;
import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.api.protocol.outgoing.OutgoingPacket;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.net.pipeline.CipherDecoder;
import pro.prysm.orion.server.net.pipeline.CipherEncoder;
import pro.prysm.orion.server.protocol.GameProfile;
import pro.prysm.orion.server.protocol.Protocol;
import pro.prysm.orion.server.protocol.handler.*;
import pro.prysm.orion.server.protocol.outgoing.login.Disconnect;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.SocketAddress;
import java.security.GeneralSecurityException;

public class Connection {
    private final ChannelHandlerContext ctx;
    private ProtocolHandler handler;
    private final Protocol protocol;
    private PacketState state;
    private byte[] sharedSecret;
    private byte[] verifyToken;
    private boolean active;
    private GameProfile profile;

    public Connection(ChannelHandlerContext ctx, Protocol protocol) {
        this.ctx = ctx;
        this.protocol = protocol;
        // We start off with a Handshake Handler since the first connection will be in the Handshake state.
        handler = new HandshakeHandler(this);
        state = PacketState.HANDSHAKE;
        active = true;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public SocketAddress getAddress() {
        return ctx.channel().remoteAddress();
    }

    public ProtocolHandler getHandler() {
        return handler;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setState(PacketState state) {
        this.state = state;
        switch(state) {
            case STATUS -> handler = new StatusHandler(this);
            case LOGIN -> handler = new LoginHandler(this);
            case PLAY -> handler = new PlayHandler(this);
        }
    }

    public PacketState getState() {
        return state;
    }

    public byte[] getSharedSecret() {
        return sharedSecret;
    }

    public void setSharedSecret(byte[] sharedSecret) {
        this.sharedSecret = sharedSecret;
    }

    public byte[] getVerifyToken() {
        return verifyToken;
    }

    public void setVerifyToken(byte[] verifyToken) {
        this.verifyToken = verifyToken;
    }

    public void setGameProfile(GameProfile profile) {
        this.profile = profile;
    }

    public GameProfile getProfile() {
        return profile;
    }

    public boolean isActive() {
        return active;
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
     * @param reason Reason for disconnect
     */
    public void disconnect(String reason) {
        if (active) {
            if (state == PacketState.LOGIN) sendPacket(new Disconnect(reason));
            // else if (state == PacketState.PLAY)
            active = false;
            ctx.flush().close().addListener(ChannelFutureListener.CLOSE);
            Orion.getLogger().finer(String.format("Forcibly closed connection %s", getAddress()));
        }
    }

    public void sendPacket(OutgoingPacket packet) {
        OutgoingPacketEvent event = new OutgoingPacketEvent();
        pro.prysm.orion.api.Orion.getEventBus().post(event, packet);
        if (!event.isCancelled()) ctx.writeAndFlush(packet);
    }
}
