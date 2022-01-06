package pro.prysm.orion.api.json;

import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import pro.prysm.orion.api.exception.ResourceNotFoundException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Config extends JSONParser {
    private final File directory;
    private final String fileName;

    public Config(Class<?> _class, File file) throws IOException {
        this.directory = file.getParentFile();
        this.fileName = file.getName();
        JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(getFile(_class)), StandardCharsets.UTF_8));
        json = JsonParser.parseReader(reader).getAsJsonObject();
    }

    public Config(File file) throws IOException {
        this.directory = file.getParentFile();
        this.fileName = file.getName();
        JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        json = JsonParser.parseReader(reader).getAsJsonObject();
    }

    private File getFile(Class<?> _class) {
        File configFile = new File(directory, fileName);
        if (!configFile.exists()) {
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
}