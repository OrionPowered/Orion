package pro.prysm.orion.server.extension;

import org.slf4j.LoggerFactory;
import pro.prysm.orion.api.exception.InvalidExtensionDescription;
import pro.prysm.orion.api.extension.AbstractExtension;
import pro.prysm.orion.api.extension.ExtensionDescription;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.extension.plugin.PluginLoader;
import pro.prysm.orion.server.util.ExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.jar.JarFile;

public abstract class AbstractClassLoader extends URLClassLoader {
    protected Field dataFolderField;
    protected Field loggerField;
    protected Field descriptionField;
    protected Field eventBusField;

    public AbstractClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
        try {
            dataFolderField = AbstractExtension.class.getDeclaredField("dataFolder");
            dataFolderField.setAccessible(true);

            loggerField = AbstractExtension.class.getDeclaredField("logger");
            loggerField.setAccessible(true);

            descriptionField = AbstractExtension.class.getDeclaredField("description");
            descriptionField.setAccessible(true);

            eventBusField = AbstractExtension.class.getDeclaredField("eventBus");
            eventBusField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            ExceptionHandler.error(e);
        }
    }

    public void loadExtensions(AbstractExtensionLoader extensionLoader, File... files) {
        for (File file : files) {
            try {
                addURL(file.toURI().toURL());
                JarFile jarFile = new JarFile(file);
                loadClasses(jarFile);

                ExtensionDescription description;

                if (extensionLoader instanceof PluginLoader)
                    description = new ExtensionDescription(jarFile, "plugin.json");
                else description = new ExtensionDescription(jarFile, "module.json");

                AbstractExtension extension = (AbstractExtension) Class.forName(description.getMainClass(), false, this)
                        .getConstructors()[0].newInstance();

                dataFolderField.set(extension, Path.of("plugins", description.getName()));
                loggerField.set(extension, LoggerFactory.getLogger(description.getName()));
                descriptionField.set(extension, description);
                eventBusField.set(extension, Orion.getEventBus());

                initializeExtension(extension);

                extensionLoader.extensions.add(extension);
                Orion.getLogger().info("Enabling plugin {} by {}", description.getName(), description.getAuthor());
                extension.generateConfig();
                extension.onEnable();
                Orion.getLogger().info("Plugin {} enabled", description.getName());
            } catch (IOException | ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | InvalidExtensionDescription e) {
                ExceptionHandler.error(e);
            }
        }
    }

    public abstract void initializeExtension(AbstractExtension extension) throws IllegalAccessException;

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

