package pro.prysm.orion.api.protocol;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

public class ServerListResponse {
    private int protocolVersion;
    private String serverName;
    private int maxPlayers;
    private int onlinePlayers;
    private Component description;

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getOnlinePlayers() {
        return onlinePlayers;
    }

    public void setOnlinePlayers(int onlinePlayers) {
        this.onlinePlayers = onlinePlayers;
    }

    public Component getDescription() {
        return description;
    }

    public void setDescription(Component description) {
        this.description = description;
    }

    public String toJsonString() {
        JsonObject response = new JsonObject();
        JsonObject version = new JsonObject();
        JsonObject players = new JsonObject();
        version.addProperty("name", serverName);
        version.addProperty("protocol", protocolVersion);
        players.addProperty("max", maxPlayers);
        players.addProperty("online", onlinePlayers);
        response.add("version", version);
        response.add("players", players);
        response.add("description", GsonComponentSerializer.gson().serializeToTree(description));
        return new Gson().toJson(response);
    }
}
