package pro.prysm.orion.api.plugin;

import lombok.Getter;
import org.slf4j.Logger;
import pro.prysm.orion.api.event.EventBus;
import pro.prysm.orion.api.exception.ResourceNotFoundException;
import pro.prysm.orion.api.json.Config;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * @author 254n_m
 * @since 12/20/21 / 9:25 PM
 * This file was created as a part of Orion
 */
@Getter
public abstract class JavaPlugin {
    private Config config;
    private File dataFolder;
    private Logger logger;
    private PluginDescription description;
    private EventBus eventBus;

    public abstract void onEnable();

    public abstract void onDisable();

    public abstract void reload();

    public void generateConfig() {
        try {
            if (!dataFolder.exists()) dataFolder.mkdir();

            try (InputStream is = getClass().getClassLoader().getResourceAsStream("config.json")) {
                if (is == null)
                    throw new ResourceNotFoundException("Could not find resource config.json in plugin " + description.getName());

                File configFile = new File(dataFolder, "config.json");
                if (configFile.exists()) return;
                Files.copy(is, configFile.toPath());
                config = new Config(configFile);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
