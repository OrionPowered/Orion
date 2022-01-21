package pro.prysm.test;

import pro.prysm.orion.api.plugin.JavaPlugin;
import pro.prysm.test.listener.PlayerJoin;
import pro.prysm.test.listener.PlayerMove;
import pro.prysm.test.listener.ServerReadyListener;

/**
 * @author 254n_m
 * @since 12/20/21 / 10:40 PM
 * This file was created as a part of Orion
 */
public class TestPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Plugin " + getDescription().getName() + " loaded");
        getEventBus().subscribe(new ServerReadyListener());
        getEventBus().subscribe(new PlayerJoin());
        getEventBus().subscribe(new PlayerMove());
        generateConfig();
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void reload() {

    }
}
