package pro.prysm.orion.common.extension.module;

import pro.prysm.orion.api.extension.AbstractExtension;
import pro.prysm.orion.common.AbstractOrion;
import pro.prysm.orion.common.extension.AbstractExtensionLoader;

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
        // TODO: let modules on server set levels
        // if (LevelProvider.class.isAssignableFrom(extension.getClass())) {
        //    Orion.getServer().setLevelProvider((LevelProvider) extension);
        // }
    }

    @Override
    public void onFinish() {
        AbstractOrion.getLogger().info("Loaded {} module(s)", extensions.size());
    }
}
