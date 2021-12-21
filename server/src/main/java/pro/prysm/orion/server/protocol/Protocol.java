package pro.prysm.orion.server.protocol;

import com.mojang.authlib.GameProfile;
import pro.prysm.orion.api.JSONConfig;
import pro.prysm.orion.api.chat.Message;
import pro.prysm.orion.api.protocol.ServerListResponse;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.protocol.outgoing.login.EncryptionRequest;
import pro.prysm.orion.server.protocol.outgoing.status.SLPResponse;

import javax.crypto.Cipher;
import java.security.*;
import java.util.concurrent.ThreadLocalRandom;

public class Protocol {
    public static final String ENCODER = "packetEncoder";
    public static final String DECODER = "packetDecoder";
    public static final String LENGTH_ENCODER = "lengthEncoder";
    public static final String LENGTH_DECODER = "lengthDecoder";
    public static final String CIPHER_ENCODER = "cipherEncoder";
    public static final String CIPHER_DECODER = "cipherDecoder";

    private final PacketRegistry packetRegistry;
    private ServerListResponse defaultSLPResponse;
    private final KeyPair keyPair;
    private int maxPlayers;

    public Protocol(JSONConfig config) {
        packetRegistry = new PacketRegistry();
        defaultSLPResponse = new ServerListResponse();
        keyPair = genKeyPair();
        reload(config);
    }

    public void reload(JSONConfig config) {
        maxPlayers = config.getInt("max-players");
        defaultSLPResponse.setProtocolVersion(757); // 1.18.1
        defaultSLPResponse.setDescription(new Message(config.getString("motd")).toComponent());
        defaultSLPResponse.setServerName(config.getString("serverName"));
        defaultSLPResponse.setMaxPlayers(maxPlayers);
    }

    private KeyPair genKeyPair() {
        KeyPair keyPair = null;
        try {
            KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
            gen.initialize(1024);
            keyPair = gen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            Orion.getLogger().warning("Error generating key pair for encryption:");
            e.printStackTrace();
        }
        return keyPair;
    }

    public byte[] decryptRSA(byte[] sharedSecret) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        return cipher.doFinal(sharedSecret);
    }

    public PacketRegistry getPacketRegistry() {
        return packetRegistry;
    }

    public void setDefaultMOTD(ServerListResponse response) {
        this.defaultSLPResponse =  response;
    }

    public void getMOTDfromConfig(JSONConfig config) {

    }

    public SLPResponse getDefaultSLP() {
        defaultSLPResponse.setOnlinePlayers(0); // TODO: implement online players
        return new SLPResponse(this, defaultSLPResponse);
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }

    public EncryptionRequest newEncryptionRequest() {
        byte[] verifyToken = new byte[4];
        ThreadLocalRandom.current().nextBytes(verifyToken);
        return new EncryptionRequest(this, keyPair.getPublic().getEncoded(), verifyToken);
    }
}
