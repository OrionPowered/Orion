package pro.prysm.orion.common.extension.plugin;

import pro.prysm.orion.api.extension.AbstractExtension;
import pro.prysm.orion.api.extension.plugin.JavaPlugin;
import pro.prysm.orion.common.AbstractOrion;
import pro.prysm.orion.common.OrionExceptionHandler;
import pro.prysm.orion.common.extension.AbstractExtensionLoader;

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
    }

    @Override
    protected void loadFields() throws NoSuchFieldException {
        serverF = JavaPlugin.class.getDeclaredField("server");
        serverF.setAccessible(true);
    }

    @Override
    public void initializeExtension(AbstractExtension extension) throws IllegalAccessException {
        serverF.set(extension, AbstractOrion.getIServer());
    }

    @Override
    public void onFinish() {
        AbstractOrion.getLogger().info("Loaded {} plugin(s)", extensions.size());
    }
}
