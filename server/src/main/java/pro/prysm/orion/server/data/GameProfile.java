package pro.prysm.orion.server.data;

import com.google.gson.JsonObject;
import pro.prysm.orion.api.json.JSONParser;

import java.util.UUID;

public class GameProfile {
    private String username;
    private UUID uniqueId;

    public GameProfile(String username, UUID uniqueId) {
        construct(username, uniqueId);
    }

    public GameProfile(JsonObject joinServerResponse) {
        JSONParser parser = new JSONParser(joinServerResponse);
        construct(parser.getString("name"), parser.getUUID("id"));
    }

    private void construct(String username, UUID uniqueId) {
        this.username = username;
        this.uniqueId = uniqueId;
    }

    public String getUsername() {
        return username;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }
}
