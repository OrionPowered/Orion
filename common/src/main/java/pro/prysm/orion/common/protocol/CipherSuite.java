package pro.prysm.orion.common.protocol;

import com.velocitypowered.natives.encryption.VelocityCipher;
import com.velocitypowered.natives.encryption.VelocityCipherFactory;
import com.velocitypowered.natives.util.Natives;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import pro.prysm.orion.common.OrionExceptionHandler;
import pro.prysm.orion.common.net.Channel;
import pro.prysm.orion.common.net.Connection;
import pro.prysm.orion.common.net.pipeline.CipherDecoder;
import pro.prysm.orion.common.net.pipeline.CipherEncoder;
import pro.prysm.orion.common.protocol.outgoing.login.EncryptionRequest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.*;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public class CipherSuite {
    private final KeyPair keyPair;
    private Cipher rsa;

    public CipherSuite() {
        keyPair = genKeyPair();
        try {
            rsa = Cipher.getInstance("RSA");
        } catch (GeneralSecurityException e) {
            OrionExceptionHandler.error(e);
        }
    }

    private KeyPair genKeyPair() {
        KeyPair keyPair = null;
        try {
            KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
            gen.initialize(1024);
            keyPair = gen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            OrionExceptionHandler.error("Error generating key pair for encryption", e);
        }
        return keyPair;
    }

    public String generateServerId(byte[] sharedSecret) {
        String id = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(sharedSecret);
            md.update(keyPair.getPublic().getEncoded());
            id = new BigInteger(md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            OrionExceptionHandler.error("Failed to create server ID. Is your java unsupported?", e);
        }
        return id;
    }

    public byte[] decryptRSA(byte[] bytes) throws GeneralSecurityException {
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("RSA");
        cipher.init(javax.crypto.Cipher.DECRYPT_MODE, keyPair.getPrivate());
        return cipher.doFinal(bytes);
    }

    public EncryptionRequest newEncryptionRequest() {
        byte[] verifyToken = new byte[4];
        ThreadLocalRandom.current().nextBytes(verifyToken);
        return new EncryptionRequest(keyPair.getPublic().getEncoded(), verifyToken);
    }

    public void startEncryption(Connection connection, byte[] secret) throws GeneralSecurityException {
        if (!connection.isActive() || secret == null) return;
        SecretKey key = new SecretKeySpec(secret, "AES");
        VelocityCipherFactory factory = Natives.cipher.get();
        VelocityCipher decryptionCipher = factory.forDecryption(key);
        VelocityCipher encryptionCipher = factory.forEncryption(key);
        ChannelHandlerContext ctx = connection.getCtx();
        ctx.channel().pipeline().addBefore(Channel.LENGTH_DECODER, Channel.CIPHER_DECODER, new CipherDecoder(decryptionCipher));
        ctx.channel().pipeline().addBefore(Channel.LENGTH_ENCODER, Channel.CIPHER_ENCODER, new CipherEncoder(encryptionCipher));
    }
}
