package pro.prysm.test.module;

import pro.prysm.orion.server.extension.module.JavaModule;

public class TestModule extends JavaModule {
    @Override
    public void onEnable() {
        getLogger().info("Example logging from Module");
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void reload() {

    }
}
