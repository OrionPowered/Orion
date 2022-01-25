package pro.prysm.orion.server.extension.module;

import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.extension.AbstractExtensionLoader;

import java.io.File;
import java.net.URL;

public class ModuleLoader extends AbstractExtensionLoader {
    public ModuleLoader() {
        super(
                new File("./modules"),
                "module.json",
                new ModuleClassLoader(new URL[]{}, ModuleLoader.class.getClassLoader())
        );
    }

    @Override
    public void onFinish() {
        Orion.getLogger().info("Loaded {} module(s)", extensions.size());
    }
}
