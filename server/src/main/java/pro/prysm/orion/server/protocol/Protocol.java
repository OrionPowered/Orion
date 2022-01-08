package pro.prysm.orion.server.protocol;

import com.google.gson.JsonParser;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import pro.prysm.orion.api.chat.Message;
import pro.prysm.orion.api.data.GameProfile;
import pro.prysm.orion.api.json.Config;
import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.api.protocol.ServerListResponse;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.data.WorldManager;
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

@Getter
public class Protocol {
    public static final String ENCODER = "packetEncoder";
    public static final String DECODER = "packetDecoder";
    public static final String LENGTH_ENCODER = "lengthEncoder";
    public static final String LENGTH_DECODER = "lengthDecoder";
    public static final String CIPHER_ENCODER = "cipherEncoder";
    public static final String CIPHER_DECODER = "cipherDecoder";
    public static final int PROTOCOL_NUMBER = 757; // 1.18.1

    private final PacketRegistry packetRegistry = new PacketRegistry();
    private final ServerListResponse slpData = new ServerListResponse();
    private final KeyPair keyPair = genKeyPair();
    private final Orion orion;
    @Getter
    private final WorldManager worldManager;
    private String sessionServer;

    private boolean onlineMode;
    private int maxPlayers;

    public Protocol(Orion orion, WorldManager worldManager, Config config) {
        this.orion = orion;
        this.worldManager = worldManager;
        reload(config);
    }

    public void reload(@NotNull Config config) {
        onlineMode = config.getBoolean("online-mode");
        maxPlayers = config.getInt("max-players");

        slpData.setProtocolVersion(PROTOCOL_NUMBER);
        slpData.setDescription(new Message(config.getString("motd")).toComponent());
        slpData.setServerName(config.getString("serverName"));
        slpData.setMaxPlayers(maxPlayers);

        sessionServer = config.getStringOrDefault("session-server", "https://sessionserver.mojang.com");
        Orion.getLogger().debug("Using session server {}", sessionServer);
    }

    private KeyPair genKeyPair() {
        KeyPair keyPair = null;
        try {
            KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
            gen.initialize(1024);
            keyPair = gen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            Orion.getLogger().warn("Error generating key pair for encryption:");
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
            Orion.getLogger().error("Failed to create server ID. Is your java unsupported?");
            e.printStackTrace();
            // TODO: shutdown here
        }
        return id;
    }

    public SLPResponse generateSLP() {
        ServerListResponse slp = new ServerListResponse();

        slp.setProtocolVersion(slpData.getProtocolVersion());
        slp.setServerName(slpData.getServerName());
        slp.setMaxPlayers(slpData.getMaxPlayers());
        slpData.setOnlinePlayers(
                (int) orion.getListener().getPipeline().getChannelHandler()
                        .getConnections().values().stream().filter(
                                (c) -> c.getState() == PacketState.PLAY).count());
        slp.setDescription(slpData.getDescription());

        return new SLPResponse(slpData);
    }

    public GameProfile join(String serverId, String username) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(
                    URI.create(String.format("%s/session/minecraft/hasJoined?username=%s&serverId=%s", sessionServer, username, serverId)
                    )).header("accept", "application/json").GET().build();
            CompletableFuture<HttpResponse<String>> future = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            HttpResponse<String> response = future.get();
            return new GameProfile(JsonParser.parseString(response.body()).getAsJsonObject());
        } catch (InterruptedException | ExecutionException e) {
            Orion.getLogger().error("Got error when attempting to authenticate session");
            e.printStackTrace();
            return null;
        }
    }

    public EncryptionRequest newEncryptionRequest() {
        byte[] verifyToken = new byte[4];
        ThreadLocalRandom.current().nextBytes(verifyToken);
        return new EncryptionRequest(keyPair.getPublic().getEncoded(), verifyToken);
    }
}
