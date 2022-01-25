package pro.prysm.orion.server.extension.plugin;

import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.extension.AbstractExtensionLoader;

import java.io.File;
import java.net.URL;

/**
 * @author 254n_m
 * @since 12/20/21 / 9:26 PM
 * This file was created as a part of Orion
 */
public class PluginLoader extends AbstractExtensionLoader {
    public PluginLoader() {
        super(
                new File("./plugins"),
                new PluginClassLoader(new URL[]{}, PluginLoader.class.getClassLoader())
        );
    }

    @Override
    public void onFinish() {
        Orion.getLogger().info("Loaded {} plugin(s)", extensions.size());
    }
}
