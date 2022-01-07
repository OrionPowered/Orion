package pro.prysm.orion.api.protocol;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Data;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

@Data
public class ServerListResponse {
    private int protocolVersion;
    private String serverName;
    private int maxPlayers;
    private int onlinePlayers;
    private Component description;

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
