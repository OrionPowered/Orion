package pro.prysm.orion.server.extension.module;

import pro.prysm.orion.api.extension.AbstractExtension;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.extension.AbstractExtensionLoader;

import java.io.File;
import java.net.URL;

public class ModuleLoader extends AbstractExtensionLoader {
    public ModuleLoader() {
        super(
                new URL[]{},
                ModuleLoader.class.getClassLoader(),
                new File("./modules"),
                "module.json"
        );
    }

    @Override
    public void initializeExtension(AbstractExtension extension) throws IllegalAccessException {
    }

    @Override
    public void onFinish() {
        Orion.getLogger().info("Loaded {} module(s)", extensions.size());
    }
}
