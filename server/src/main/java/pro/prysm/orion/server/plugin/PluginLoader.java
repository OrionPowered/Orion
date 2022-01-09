package pro.prysm.orion.server.plugin;

import pro.prysm.orion.api.plugin.JavaPlugin;
import pro.prysm.orion.server.Orion;

import java.io.File;
import java.io.FilenameFilter;
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
    private final PluginClassLoader pluginClassLoader = new PluginClassLoader(new URL[]{}, this.getClass().getClassLoader(), this);

    public PluginLoader() {
        loadPlugins();
    }

    private void loadPlugins() {
        Orion.getLogger().info("Loading plugins!");

        if (!pluginFolder.exists()) {
            //noinspection ResultOfMethodCallIgnored
            pluginFolder.mkdirs();
        }



        File[] pluginFiles = pluginFolder.listFiles();
        if (pluginFiles == null) return;

        FilenameFilter filter = (dir, name) -> name.endsWith(".jar");
        List<File> jars = new ArrayList<>();
        for (File file : pluginFiles) {
            if (file.isDirectory()) continue;

            if (filter.accept(file.getParentFile(), file.getName())) {
                jars.add(file);
            }
        }

        pluginClassLoader.loadPlugins(jars.toArray(File[]::new));
    }
}
