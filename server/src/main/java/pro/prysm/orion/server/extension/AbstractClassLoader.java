package pro.prysm.orion.server.extension;

import pro.prysm.orion.server.util.ExceptionHandler;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;

public abstract class AbstractClassLoader extends URLClassLoader {
    public AbstractClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    protected void loadClasses(JarFile jarFile) {
        jarFile.stream(). // Load all the classes in the plugin
                filter(e -> e.getName().endsWith(".class")).map(je -> je.getName().replace("/", ".").replace(".class", "")).toList().forEach(str -> {
            try {
                loadClass(str);
            } catch (ClassNotFoundException e) {
                ExceptionHandler.error(e);
            }
        });
    }
}

