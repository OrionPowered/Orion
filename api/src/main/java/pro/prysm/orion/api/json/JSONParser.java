package pro.prysm.orion.api.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.UUID;

public class JSONParser {
    protected JsonObject json;

    protected JSONParser() {
    }

    public JSONParser(JsonObject json) {
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
        JsonElement e = get(key);
        return (e == null)
                ? null
                : e.getAsString();
    }

    public String getStringOrDefault(String key, String defaultString) {
        String string = getString(key);
        return (string == null)
                ? defaultString
                : string;
    }

    public UUID getUUID(String key) {
        return (getString(key) == null)
                ? null
                : UUID.fromString(getString(key).replaceFirst(
                "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5"
        ));
    }

    public boolean getBoolean(String key) {
        JsonElement e = get(key);
        return e.getAsBoolean();
    }

    public int getInt(String key) {
        JsonElement e = get(key);
        return e.getAsInt();
    }

    public long getLong(String key) {
        JsonElement e = get(key);
        return e.getAsLong();
    }

    public double getDouble(String key) {
        JsonElement e = get(key);
        return e.getAsDouble();
    }

    public String getStringOrDefault(String key, String defaultString) {
        String string = getString(key);
        return (string == null)
                ? defaultString
                : string;
    }
}
