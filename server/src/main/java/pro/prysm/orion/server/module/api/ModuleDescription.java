package pro.prysm.orion.server.module.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import pro.prysm.orion.server.util.ExceptionHandler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.jar.JarFile;

@Getter
public class ModuleDescription {
    private final String name;
    private final String mainClass;
    private final String author;

    public ModuleDescription(JarFile file) throws InvalidModuleException {
        try (InputStream desc = file.getInputStream(file.getEntry("module.json"))) {
            if (desc == null) throw new InvalidModuleException("Missing module.json");

            try (InputStreamReader isr = new InputStreamReader(desc)) {
                try (BufferedReader reader = new BufferedReader(isr)) {
                    JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

                    name = jsonObject.get("name").getAsString();
                    mainClass = jsonObject.get("main").getAsString();
                    author = jsonObject.get("author").getAsString();
                }
            }
        } catch (InvalidModuleException e) {
            ExceptionHandler.error(e);
        } catch (Exception e) {
            throw new InvalidModuleException(e.getMessage());
        }
    }
}
