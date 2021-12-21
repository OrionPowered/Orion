package pro.prysm.orion.api.plugin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pro.prysm.orion.api.exception.InvalidPluginException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
/**
 * @author 254n_m
 * @since 12/20/21 / 10:48 PM
 * This file was created as a part of Orion
 */
public class PluginDescription {
    private String name;
    private String mainClass;
    private String author;

    public PluginDescription(JarFile file) {
        try {
            InputStream desc = file.getInputStream(file.getEntry("plugin.json"));
            if (desc == null) throw new InvalidPluginException("Missing plugin.json");
            InputStreamReader isr = new InputStreamReader(desc);
            BufferedReader reader = new BufferedReader(isr);
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

            desc.close();
            isr.close();
            reader.close();

            name = jsonObject.get("name").getAsString();
            mainClass = jsonObject.get("main").getAsString();
            author = jsonObject.get("author").getAsString();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getMainClass() {
        return mainClass;
    }

    public String getAuthor() {
        return author;
    }
}
