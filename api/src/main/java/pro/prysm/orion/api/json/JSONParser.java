package pro.prysm.orion.api.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.UUID;

public class JSONParser {
    protected JsonObject json;

    public JSONParser(JsonObject json) {
        this.json = json;
    }

    public JSONParser() { }

    public void setJson(JsonObject json) {
        this.json = json;
    }

    public JsonElement get(String key) {
        JsonElement output;
        if (key.contains(".")) {
            String[] parts = key.split("\\.");
            String finalKey = parts[parts.length - 1];
            JsonObject joe = json;
            for (int i = 0; i < parts.length - 1; i++) joe = (JsonObject) joe.get(parts[i]);
            output = joe.get(finalKey);
        } else output = json.get(key);
        return output;
    }

    public String getString(String key) {
        return get(key).getAsString();
    }

    public UUID getUUID(String key) {
        return UUID.fromString(getString(key));
    }

    public boolean getBoolean(String key) {
        return get(key).getAsBoolean();
    }

    public int getInt(String key) {
        return get(key).getAsInt();
    }

    public long getLong(String key) {
        return get(key).getAsLong();
    }

    public double getDouble(String key) {
        return get(key).getAsDouble();
    }

}
