package pro.prysm.orion.api.json;

import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import pro.prysm.orion.api.exception.ResourceNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config extends JSONParser {
    public Config(ClassLoader classLoader, Path configFile, String resourcePath) throws IOException {
        if (!Files.exists(configFile)) {
            try {
                try (InputStream is = classLoader.getResourceAsStream(resourcePath)) {
                    if (is == null) throw new ResourceNotFoundException("Could not find resource " + resourcePath + " in jar");
                    Files.copy(is, configFile);
                }
            } catch (IOException | ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }

        JsonReader reader = new JsonReader(new InputStreamReader(Files.newInputStream(configFile), StandardCharsets.UTF_8));
        json = JsonParser.parseReader(reader).getAsJsonObject();
    }

    public Config(Path configFile) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(Files.newInputStream(configFile), StandardCharsets.UTF_8));
        json = JsonParser.parseReader(reader).getAsJsonObject();
    }
}