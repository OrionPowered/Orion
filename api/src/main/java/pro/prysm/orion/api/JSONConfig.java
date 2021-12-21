package pro.prysm.orion.api;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import pro.prysm.orion.api.exception.ResourceNotFoundException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class JSONConfig {
    private final File directory;
    private final String fileName;
    private final JsonObject json;

    public JSONConfig(Class<?> _class, File file) throws IOException {
        this.directory = file.getParentFile();
        this.fileName = file.getName();
        JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(getFile(_class)), StandardCharsets.UTF_8));
        json = JsonParser.parseReader(reader).getAsJsonObject();
    }

    private File getFile(Class<?> _class) {
        File configFile = new File(directory, fileName);
        if(!configFile.exists()) {
            try {
                InputStream is = _class.getClassLoader().getResourceAsStream(fileName);
                if (is == null) throw new ResourceNotFoundException("Could not find resource " + fileName + " in jar");
                Files.copy(is, configFile.toPath());
                is.close();
            } catch (IOException | ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        return configFile;
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