package pro.prysm.orion.api.entity.player;

import com.google.gson.JsonArray;
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
    private ProfileProperty[] properties;

    /**
     * Creates an online GameProfile from the Mojang API response
     *
     * @param joinServerResponse Response from Mojang
     */
    public GameProfile(JsonObject joinServerResponse) {
        JSONParser parser = new JSONParser(joinServerResponse);
        this.username = parser.getString("name");
        this.uniqueId = parser.getUUID("id");
        JsonArray propertiesArray = parser.getArray("properties");
        properties = new ProfileProperty[propertiesArray.size()];
        for (int i = 0; i < propertiesArray.size(); i++) {
            JSONParser propertyParser = new JSONParser((JsonObject) propertiesArray.get(i));
            properties[i] = new ProfileProperty(
                    propertyParser.getString("name"),
                    propertyParser.getString("value"),
                    propertyParser.getString("signature")
            );
        }
    }

    /**
     * Creates an Offline GameProfile
     *
     * @param username Player username
     * @param uniqueId Player UUID
     */
    public GameProfile(String username, UUID uniqueId) {
        this.username = username;
        this.uniqueId = uniqueId;
    }
}
