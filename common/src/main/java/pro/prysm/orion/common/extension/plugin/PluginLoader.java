package pro.prysm.orion.common.extension.plugin;

import pro.prysm.orion.api.extension.AbstractExtension;
import pro.prysm.orion.api.extension.plugin.JavaPlugin;
import pro.prysm.orion.common.extension.AbstractExtensionLoader;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.util.ExceptionHandler;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;

/**
 * @author 254n_m
 * @since 12/20/21 / 9:26 PM
 * This file was created as a part of Orion
 */
public class PluginLoader extends AbstractExtensionLoader {
    private Field serverF;

    public PluginLoader() {
        super(
                new URL[]{},
                PluginLoader.class.getClassLoader(),
                new File("./plugins"),
                "plugin.json"
        );

        try {
            serverF = JavaPlugin.class.getDeclaredField("server");
            serverF.setAccessible(true);
        } catch (Exception e) {
            ExceptionHandler.error(e);
        }

    }

    @Override
    public void initializeExtension(AbstractExtension extension) throws IllegalAccessException {
        serverF.set(extension, Orion.getServer());
    }

    @Override
    public void onFinish() {
        Orion.getLogger().info("Loaded {} plugin(s)", extensions.size());
    }
}
