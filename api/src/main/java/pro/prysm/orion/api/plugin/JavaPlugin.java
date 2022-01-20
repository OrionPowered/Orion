package pro.prysm.orion.api.plugin;

import lombok.Getter;
import org.slf4j.Logger;
import pro.prysm.orion.api.event.EventBus;
import pro.prysm.orion.api.json.Config;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author 254n_m
 * @since 12/20/21 / 9:25 PM
 * This file was created as a part of Orion
 */
@Getter
public abstract class JavaPlugin {
    private Path dataFolder;
    private Config config;
    private PluginDescription description;
    private Logger logger;
    private EventBus eventBus;


    public abstract void onEnable();

    public abstract void onDisable();

    public abstract void reload();

    public void generateConfig() {
        try {
            if (!Files.exists(dataFolder)) Files.createDirectories(dataFolder);
            config = new Config(getClass().getClassLoader(), dataFolder.resolve("config.json"), "config.json");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
