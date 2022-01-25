package pro.prysm.orion.api.extension;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pro.prysm.orion.api.exception.InvalidExtensionDescription;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.jar.JarFile;

/**
 * @author 254n_m
 * @since 12/20/21 / 10:48 PM
 * This file was created as a part of Orion
 */
public class ExtensionDescription {
    private final String name;
    private final String mainClass;
    private final String author;

    public ExtensionDescription(JarFile file, String fileName) throws InvalidExtensionDescription {
        try (InputStream desc = file.getInputStream(file.getEntry(fileName))) {
            if (desc == null) throw new InvalidExtensionDescription("Missing " + fileName);

            try (InputStreamReader isr = new InputStreamReader(desc)) {
                try (BufferedReader reader = new BufferedReader(isr)) {
                    JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

                    name = jsonObject.get("name").getAsString();
                    mainClass = jsonObject.get("main").getAsString();
                    author = jsonObject.get("author").getAsString();
                }
            }
        } catch (InvalidExtensionDescription e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidExtensionDescription(e.getMessage());
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
