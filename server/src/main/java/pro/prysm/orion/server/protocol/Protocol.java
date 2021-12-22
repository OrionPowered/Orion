package pro.prysm.orion.server.protocol;

import com.google.gson.JsonParser;
import pro.prysm.orion.api.json.Config;
import pro.prysm.orion.api.chat.Message;
import pro.prysm.orion.api.protocol.ServerListResponse;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.protocol.outgoing.login.EncryptionRequest;
import pro.prysm.orion.server.protocol.outgoing.status.SLPResponse;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

public class Protocol {
    public static final String ENCODER = "packetEncoder";
    public static final String DECODER = "packetDecoder";
    public static final String LENGTH_ENCODER = "lengthEncoder";
    public static final String LENGTH_DECODER = "lengthDecoder";
    public static final String CIPHER_ENCODER = "cipherEncoder";
    public static final String CIPHER_DECODER = "cipherDecoder";

    private final PacketRegistry packetRegistry;
    private String sessionServer;
    private ServerListResponse defaultSLPResponse;
    private final KeyPair keyPair;
    private int maxPlayers;

    public Protocol(Config config) {
        packetRegistry = new PacketRegistry();
        defaultSLPResponse = new ServerListResponse();
        keyPair = genKeyPair();
        reload(config);
    }

    public void reload(Config config) {
        maxPlayers = config.getInt("max-players");
        defaultSLPResponse.setProtocolVersion(757); // 1.18.1
        defaultSLPResponse.setDescription(new Message(config.getString("motd")).toComponent());
        defaultSLPResponse.setServerName(config.getString("serverName"));
        defaultSLPResponse.setMaxPlayers(maxPlayers);
        sessionServer = config.getStringOrDefault("session-server", "https://sessionserver.mojang.com");
        Orion.getLogger().finer(String.format("Using session server %s", sessionServer));
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

    public byte[] decryptRSA(byte[] bytes) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        return cipher.doFinal(bytes);
    }

    public String generateServerId(byte[] sharedSecret) {
        String id = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(sharedSecret);
            md.update(keyPair.getPublic().getEncoded());
            id = new BigInteger(md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            Orion.getLogger().severe("Failed to create server ID:");
            e.printStackTrace();
            // TODO: shutdown here
        }
        return id;
    }

    public PacketRegistry getPacketRegistry() {
        return packetRegistry;
    }

    public void setDefaultMOTD(ServerListResponse response) {
        this.defaultSLPResponse =  response;
    }

    public SLPResponse getDefaultSLP() {
        defaultSLPResponse.setOnlinePlayers(0); // TODO: implement online players
        return new SLPResponse(defaultSLPResponse);
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }

    public GameProfile join(String serverId, String username) {
        GameProfile profile = null;
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(
                    URI.create(String.format("%s/session/minecraft/hasJoined?username=%s&serverId=%s", sessionServer, username, serverId)
                )).header("accept", "application/json").GET().build();
            CompletableFuture<HttpResponse<String>> future = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            HttpResponse<String> response = future.get();
            profile = new GameProfile(JsonParser.parseString(response.body()).getAsJsonObject());
        } catch(InterruptedException | ExecutionException e) {
            Orion.getLogger().severe("Got error when attempting to authenticate session");
            e.printStackTrace();
        }
        return profile;
    }

    public EncryptionRequest newEncryptionRequest() {
        byte[] verifyToken = new byte[4];
        ThreadLocalRandom.current().nextBytes(verifyToken);
        return new EncryptionRequest(keyPair.getPublic().getEncoded(), verifyToken);
    }
}
