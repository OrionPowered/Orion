package pro.prysm.orion.api.data;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pro.prysm.orion.api.json.JSONParser;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class GameProfile {
    private String username;
    private UUID uniqueId;

    public GameProfile(JsonObject joinServerResponse) {
        JSONParser parser = new JSONParser(joinServerResponse);
        this.username = parser.getString("name");
        this.uniqueId = parser.getUUID("id");
        // TODO: skin parts
    }

    private void construct(String username, UUID uniqueId) {

    }
}
