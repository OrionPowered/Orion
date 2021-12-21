package pro.prysm.orion.server.net;

import com.velocitypowered.natives.encryption.VelocityCipher;
import com.velocitypowered.natives.encryption.VelocityCipherFactory;
import com.velocitypowered.natives.util.Natives;
import io.netty.channel.ChannelHandlerContext;
import pro.prysm.orion.api.event.events.OutgoingPacketEvent;
import pro.prysm.orion.api.protocol.Packet;
import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.api.protocol.outgoing.OutgoingPacket;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.net.pipeline.CipherDecoder;
import pro.prysm.orion.server.net.pipeline.CipherEncoder;
import pro.prysm.orion.server.protocol.Protocol;
import pro.prysm.orion.server.protocol.outgoing.login.Disconnect;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.SocketAddress;
import java.security.GeneralSecurityException;

public class Connection {
    private final ChannelHandlerContext ctx;
    private PacketState state;
    private byte[] sharedSecret;
    private byte[] verifyToken;
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

    public boolean isActive() {
        return active;
    }

    public void enableEncryption(byte[] secret) throws GeneralSecurityException {
        if (!active || secret == null) return;
        SecretKey key = new SecretKeySpec(secret, "AES");
        VelocityCipherFactory factory = Natives.cipher.get();
        VelocityCipher decryptionCipher = factory.forDecryption(key);
        VelocityCipher encryptionCipher = factory.forEncryption(key);
        ctx.channel().pipeline().addBefore(Protocol.ENCODER, Protocol.CIPHER_ENCODER, new CipherEncoder(encryptionCipher));
        ctx.channel().pipeline().addBefore(Protocol.DECODER, Protocol.CIPHER_DECODER, new CipherDecoder(decryptionCipher));
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
        OutgoingPacketEvent event = new OutgoingPacketEvent();
        pro.prysm.orion.api.Orion.getEventBus().post(event, packet);
        if (!event.isCancelled()) ctx.writeAndFlush(packet);
    }
}
