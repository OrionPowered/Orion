package pro.prysm.test;

import pro.prysm.orion.api.Orion;
import pro.prysm.orion.api.plugin.JavaPlugin;
import pro.prysm.test.listener.ServerReadyListener;
import pro.prysm.test.listener.TestPacketClass;

/**
 * @author 254n_m
 * @since 12/20/21 / 10:40 PM
 * This file was created as a part of Orion
 */
public class TestPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Plugin " + getDescription().getName() + " loaded");
        Orion.getEventBus().subscribe(new ServerReadyListener());
        Orion.getEventBus().subscribe(new TestPacketClass());
        generateConfig();
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void reload() {

    }
}
