package pro.prysm.orion.common.protocol.auth;

import com.google.gson.JsonParser;
import pro.prysm.orion.api.entity.player.GameProfile;
import pro.prysm.orion.common.OrionExceptionHandler;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class YggdrasilAuth implements AuthenticationProvider {
    private final String sessionServer;

    public YggdrasilAuth(String sessionServer) {
        this.sessionServer = sessionServer;
    }

    @Override
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
            OrionExceptionHandler.error("Got error when attempting to authenticate session", e);
            return null;
        }
    }
}
