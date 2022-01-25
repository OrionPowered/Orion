package pro.prysm.orion.server.extension.module;

import pro.prysm.orion.api.extension.AbstractExtension;
import pro.prysm.orion.server.extension.AbstractClassLoader;

import java.net.URL;

public class ModuleClassLoader extends AbstractClassLoader {
    public ModuleClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    public void initializeExtension(AbstractExtension extension) throws IllegalAccessException {
        // We don't have to set any additional fields for modules
    }
}
