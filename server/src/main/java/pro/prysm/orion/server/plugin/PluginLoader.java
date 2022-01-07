package pro.prysm.orion.server.plugin;

import pro.prysm.orion.api.plugin.JavaPlugin;
import pro.prysm.orion.server.Orion;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 254n_m
 * @since 12/20/21 / 9:26 PM
 * This file was created as a part of Orion
 */
public class PluginLoader {
    protected final List<JavaPlugin> plugins = new ArrayList<>();
    private final File pluginFolder = new File("./plugins");
    private final File moduleFolder = new File("./modules");
    private final PluginClassLoader pluginClassLoader = new PluginClassLoader(new URL[]{}, this.getClass().getClassLoader(), this);

    public PluginLoader() {
        loadPlugins();
    }

    private void loadPlugins() {
        Orion.getLogger().info( "Loading plugins!");
        if (!pluginFolder.exists()) pluginFolder.mkdir();
        if (!moduleFolder.exists()) moduleFolder.mkdir();
        List<File> jars = new ArrayList<>();
        for (File file : pluginFolder.listFiles()) {
            if (file.isDirectory()) continue;
            if (getFileExtension(file).equalsIgnoreCase(".jar")) {
                jars.add(file);
            } else Orion.getLogger().warn("File " + file.getName() + " in the plugins folder is not a JarFile");
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
