package pro.prysm.orion.server.module;

import org.slf4j.LoggerFactory;
import pro.prysm.orion.api.exception.InvalidPluginException;
import pro.prysm.orion.api.plugin.JavaPlugin;
import pro.prysm.orion.api.plugin.PluginDescription;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.module.api.JavaModule;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;

public class ModuleClassLoader extends URLClassLoader {
    private final ModuleLoader loader;
    private Field dataFolderF;
    private Field loggerF;
    private Field descriptionF;
    private Field eventBusF;

    public ModuleClassLoader(URL[] urls, ClassLoader parent, ModuleLoader loader) {
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

    public void loadModules(File... files) {
        for (File file : files) {
            try {
                addURL(file.toURI().toURL());

                JarFile jarFile = new JarFile(file);

                loadClasses(jarFile);

                PluginDescription description = new PluginDescription(jarFile);
                JavaModule module = (JavaModule) Class.forName(description.getMainClass(), false, this)
                        .getConstructors()[0].newInstance();

                dataFolderF.set(module, new File("./modules", description.getName()));
                loggerF.set(module, LoggerFactory.getLogger(description.getName()));
                descriptionF.set(module, description);
                eventBusF.set(module, Orion.getEventBus());
                loader.getModules().add(module);
                module.onEnable();
            } catch (IOException | ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | InvalidPluginException e) {
                Orion.getLogger().warn(String.format("Error loading module %s: %s", file.getName(), e.getMessage()));
            }
        }
    }

    private void loadClasses(JarFile jarFile) {
        jarFile.stream(). // Load all the classes in the module
                filter(e -> e.getName().endsWith(".class")).map(je -> je.getName().replace("/", ".").replace(".class", "")).toList().forEach(str -> {
            try {
                loadClass(str);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
