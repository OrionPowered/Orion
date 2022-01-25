package pro.prysm.orion.server.extension.plugin;

import pro.prysm.orion.api.extension.AbstractExtension;
import pro.prysm.orion.api.extension.plugin.JavaPlugin;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.extension.AbstractClassLoader;
import pro.prysm.orion.server.util.ExceptionHandler;

import java.lang.reflect.Field;
import java.net.URL;

/**
 * @author 254n_m
 * @since 12/20/21 / 9:29 PM
 * This file was created as a part of Orion
 */
public class PluginClassLoader extends AbstractClassLoader {
    private Field serverF;

    public PluginClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
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
}