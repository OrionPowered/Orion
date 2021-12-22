package pro.prysm.orion.server.plugin;

import pro.prysm.orion.api.plugin.JavaPlugin;
import pro.prysm.orion.server.Orion;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * @author 254n_m
 * @since 12/20/21 / 9:26 PM
 * This file was created as a part of Orion
 */
public class PluginLoader {
    private final File pluginFolder = new File("./plugins");
    private final File moduleFolder = new File("./modules");
    private PluginClassLoader pluginClassLoader;
    protected final List<JavaPlugin> plugins = new ArrayList<>();

    public PluginLoader() {
        loadPlugins();
    }

    private void loadPlugins() {
        Orion.getLogger().log(Level.INFO, "Loading plugins!");
        pluginClassLoader = new PluginClassLoader(new URL[]{}, this.getClass().getClassLoader(), this);
        if (!pluginFolder.exists()) pluginFolder.mkdir();
        if (!moduleFolder.exists()) moduleFolder.mkdir();
        List<File> jars = new ArrayList<>();
        for (File file : pluginFolder.listFiles()) {
            if (file.isDirectory()) continue;
            if (getFileExtension(file).equalsIgnoreCase(".jar")) {
                jars.add(file);
            } else Orion.getLogger().warning("File " + file.getName() + " in the plugins folder is not a JarFile");
        }
        pluginClassLoader.loadPlugin(jars.toArray(File[]::new));
    }

    public String getFileExtension(File file) {
        String name = file.getName();
        int index = name.lastIndexOf(".");
        if (index == -1) {
            return "";
        }
        return name.substring(index);
    }

    private URL toURL(File file) {
        try {
            return file.toURI().toURL();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}