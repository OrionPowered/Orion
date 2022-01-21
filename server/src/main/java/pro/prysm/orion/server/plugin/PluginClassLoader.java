package pro.prysm.orion.server.plugin;

import org.slf4j.LoggerFactory;
import pro.prysm.orion.api.exception.InvalidPluginException;
import pro.prysm.orion.api.plugin.JavaPlugin;
import pro.prysm.orion.api.plugin.PluginDescription;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.util.ExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.jar.JarFile;

/**
 * @author 254n_m
 * @since 12/20/21 / 9:29 PM
 * This file was created as a part of Orion
 */
public class PluginClassLoader extends URLClassLoader {
    private final PluginLoader loader;
    private Field dataFolderF;
    private Field loggerF;
    private Field descriptionF;
    private Field eventBusF;
    private Field serverF;

    public PluginClassLoader(URL[] urls, ClassLoader parent, PluginLoader loader) {
        super(urls, parent);
        this.loader = loader;
        try {
            dataFolderF = JavaPlugin.class.getDeclaredField("dataFolder");
            dataFolderF.setAccessible(true);
            loggerF = JavaPlugin.class.getDeclaredField("logger");
            loggerF.setAccessible(true);
            descriptionF = JavaPlugin.class.getDeclaredField("description");
            descriptionF.setAccessible(true);
            eventBusF = JavaPlugin.class.getDeclaredField("eventBus");
            eventBusF.setAccessible(true);
            serverF = JavaPlugin.class.getDeclaredField("server");
            serverF.setAccessible(true);
        } catch (Exception e) {
            ExceptionHandler.error(e);
        }
    }

    public void loadPlugins(File... files) {
        for (File file : files) {
            try {
                addURL(file.toURI().toURL());

                JarFile jarFile = new JarFile(file);

                loadClasses(jarFile);

                PluginDescription description = new PluginDescription(jarFile);
                JavaPlugin plugin = (JavaPlugin) Class.forName(description.getMainClass(), false, this)
                        .getConstructors()[0].newInstance();

                dataFolderF.set(plugin, Path.of("plugins", description.getName()));
                loggerF.set(plugin, LoggerFactory.getLogger(description.getName()));
                descriptionF.set(plugin, description);
                eventBusF.set(plugin, Orion.getEventBus());
                serverF.set(plugin, Orion.getServer());
                loader.plugins.add(plugin);
                plugin.onEnable();
            } catch (IOException | ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | InvalidPluginException e) {
                ExceptionHandler.error(e);
            }
        }
    }

    private void loadClasses(JarFile jarFile) {
        jarFile.stream(). // Load all the classes in the plugin
                filter(e -> e.getName().endsWith(".class")).map(je -> je.getName().replace("/", ".").replace(".class", "")).toList().forEach(str -> {
            try {
                loadClass(str);
            } catch (ClassNotFoundException e) {
                ExceptionHandler.error(e);
            }
        });
    }
}
