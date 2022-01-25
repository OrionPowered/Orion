package pro.prysm.test.plugin;

import pro.prysm.orion.api.extension.plugin.JavaPlugin;
import pro.prysm.test.plugin.listener.PlayerJoin;
import pro.prysm.test.plugin.listener.PlayerMove;
import pro.prysm.test.plugin.listener.ServerReadyListener;

/**
 * @author 254n_m
 * @since 12/20/21 / 10:40 PM
 * This file was created as a part of Orion
 */
public class TestPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getEventBus().subscribe(new ServerReadyListener());
        getEventBus().subscribe(new PlayerJoin());
        getEventBus().subscribe(new PlayerMove());
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void reload() {

    }
}
