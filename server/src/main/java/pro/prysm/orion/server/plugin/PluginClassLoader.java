package pro.prysm.orion.server.plugin;

import pro.prysm.orion.api.plugin.JavaPlugin;
import pro.prysm.orion.api.plugin.PluginDescription;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.util.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;
import java.util.logging.Level;

/**
 * @author 254n_m
 * @since 12/20/21 / 9:29 PM
 * This file was created as a part of Orion
 */
public class PluginClassLoader extends URLClassLoader {
    private Field dataFolderF;
    private Field loggerF;
    private Field descriptionF;
    private Field eventBusF;
    private final PluginLoader loader;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadPlugin(File... files) {
        for (File file : files) {
            try {
                addURL(file.toURI().toURL());
                JarFile jarFile = new JarFile(file);
                loadClasses(jarFile);
                PluginDescription description = new PluginDescription(jarFile);
                JavaPlugin plugin = (JavaPlugin)  Class.forName(description.getMainClass(), false, this).getConstructors()[0].newInstance();
                dataFolderF.set(plugin, new File("./plugins", description.getName()));
                loggerF.set(plugin, new Logger(description.getName(), Level.ALL));
                descriptionF.set(plugin, description);
                eventBusF.set(plugin, Orion.getEventBus());
                loader.plugins.add(plugin);
                plugin.onEnable();
            } catch (IOException | ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadClasses(JarFile jarFile) {
        jarFile.stream(). //Load all the classes in the plugin
                filter(e -> e.getName().endsWith(".class")).map(je -> je.getName().replace("/", ".").replace(".class", "")).toList().forEach(str -> {
            try {
                loadClass(str);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
