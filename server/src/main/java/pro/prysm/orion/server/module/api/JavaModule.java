package pro.prysm.orion.server.module.api;

import lombok.Getter;
import org.slf4j.Logger;
import pro.prysm.orion.api.event.EventBus;
import pro.prysm.orion.api.json.Config;
import pro.prysm.orion.server.util.ExceptionHandler;

import java.nio.file.Files;
import java.nio.file.Path;

@Getter
public abstract class JavaModule {
    protected Config config;
    protected Path dataFolder;
    protected Logger logger;
    private EventBus eventBus;

    public abstract void onEnable();

    public abstract void onDisable();

    public abstract void reload();

    public void generateConfig() {
        try {
            if (!Files.exists(dataFolder)) Files.createDirectories(dataFolder);

            config = new Config(getClass().getClassLoader(), dataFolder.resolve("config.json"), "config.json");
        } catch (Throwable t) {
            ExceptionHandler.error(t);
        }
    }
}
