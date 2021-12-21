package pro.prysm.orion.server.plugin;

import pro.prysm.orion.server.Orion;

import java.io.File;
import java.util.logging.Level;

/**
 * @author 254n_m
 * @since 12/20/21 / 9:26 PM
 * This file was created as a part of Orion
 */
public class PluginLoader {
    private final File pluginFolder = new File("./plugins");
    private final File moduleFolder = new File("./modules");

    public PluginLoader() {
        Orion.getLogger().log(Level.INFO, "Loading plugins!");
        if (!pluginFolder.exists()) pluginFolder.mkdir();
        if (!moduleFolder.exists()) moduleFolder.mkdir();

    }
}
