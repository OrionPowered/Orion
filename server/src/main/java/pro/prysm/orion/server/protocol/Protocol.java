package pro.prysm.orion.server.protocol;

import com.google.gson.JsonParser;
import lombok.Getter;
import pro.prysm.orion.api.entity.player.GameMode;
import pro.prysm.orion.api.entity.player.GameProfile;
import pro.prysm.orion.api.protocol.status.ServerListResponse;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.Server;
import pro.prysm.orion.server.protocol.outgoing.login.EncryptionRequest;
import pro.prysm.orion.server.protocol.outgoing.play.JoinGame;
import pro.prysm.orion.server.protocol.outgoing.status.SLPResponse;
import pro.prysm.orion.server.util.ExceptionHandler;
import pro.prysm.orion.server.world.LevelManager;
import pro.prysm.orion.server.world.dimension.DimensionProvider;

import javax.crypto.Cipher;
import java.io.IOException;
import java.io.InputStream;
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
    private final JoinGame joinGamePacket;
    private final KeyPair keyPair = genKeyPair();

    public Protocol(Server server) {
        reload(server);
        joinGamePacket = createJoinGamePacket(server);
    }

    public void reload(Server server) {
        slpData.getVersion().setProtocol(PROTOCOL_NUMBER);
        slpData.getVersion().setName(server.getName());
        slpData.setDescription(server.getMotdComponent());
        slpData.getPlayers().setMax(server.getMaxPlayers());

        try (InputStream is = getClass().getClassLoader().getResourceAsStream("prysm.png")) {
            if (is == null)
                return;
            slpData.setFavicon(ServerListResponse.generateFavicon(is));
        } catch (IOException e) {
            ExceptionHandler.error(e);
        }
        if (server.isOnlineMode())
            Orion.getLogger().warn("Orion is running in offline mode. Players will not be authenticated!");
        else Orion.getLogger().debug("Using session server {}", server.getSessionServer());
    }

    private JoinGame createJoinGamePacket(Server server) {
        LevelManager levelManager = server.getLevelManager();
        JoinGame packet = new JoinGame();
        DimensionProvider dimension = levelManager.getDimension();

        packet.setDimensionCodec(dimension.getDimension());
        packet.setWorlds(levelManager.getWorlds());
        packet.setHashedSeed(12345678);                         // TODO: Implement Seed
        packet.setMaxPlayers(server.getMaxPlayers());
        packet.setViewDistance(10);                             // TODO: View distance
        packet.setSimulationDistance(10);                       // TODO: Simulation distance
        packet.setReducedDebugInfo(false);
        packet.setRespawnScreen(true);
        packet.setDebug(false);
        packet.setFlat(false);

        if (levelManager.isVoidWorld()) {
            packet.setGamemode(GameMode.SPECTATOR);
            packet.setPreviousGamemode(GameMode.SPECTATOR);
            packet.setDimension(dimension.getDimensionType("minecraft:the_end"));
            packet.setWorldName("void");
        } else {
            packet.setDimension(dimension.getDimensionType("minecraft:overworld"));
        }

        return packet;
    }

    private KeyPair genKeyPair() {
        KeyPair keyPair = null;
        try {
            KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
            gen.initialize(1024);
            keyPair = gen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            ExceptionHandler.error("Error generating key pair for encryption", e);
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
            ExceptionHandler.error("Failed to create server ID. Is your java unsupported?", e);
        }
        return id;
    }

    public SLPResponse generateSLP() {
        ServerListResponse slp = new ServerListResponse();

        slp.getVersion().setProtocol(slpData.getVersion().getProtocol());
        slp.getVersion().setName(slpData.getVersion().getName());
        slp.getPlayers().setMax(slpData.getPlayers().getMax());
        slpData.getPlayers().setOnline(Orion.getServer().getPlayers().size());
        slp.setDescription(slpData.getDescription());

        return new SLPResponse(slpData);
    }

    public GameProfile join(String serverId, String username) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(
                    URI.create(String.format("%s/session/minecraft/hasJoined?username=%s&serverId=%s", Orion.getServer().getSessionServer(), username, serverId)
                    )).header("accept", "application/json").GET().build();
            CompletableFuture<HttpResponse<String>> future = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            HttpResponse<String> response = future.get();
            return new GameProfile(JsonParser.parseString(response.body()).getAsJsonObject());
        } catch (InterruptedException | ExecutionException e) {
            ExceptionHandler.error("Got error when attempting to authenticate session", e);
            return null;
        }
    }

    public EncryptionRequest newEncryptionRequest() {
        byte[] verifyToken = new byte[4];
        ThreadLocalRandom.current().nextBytes(verifyToken);
        return new EncryptionRequest(keyPair.getPublic().getEncoded(), verifyToken);
    }
}
