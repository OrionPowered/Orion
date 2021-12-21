package pro.prysm.orion.server.plugin;

import pro.prysm.orion.api.exception.InvalidPluginException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author 254n_m
 * @since 12/20/21 / 9:29 PM
 * This file was created as a part of Orion
 */
public class PluginClassLoader extends URLClassLoader {
    public PluginClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public void loadPlugin(File... files) {
        for (File file : files) {
            try {
                if (!getFileExtension(file).equals(".jar")) { //Check if the file is a jar
                    throw new InvalidPluginException("File " + file.getName() + " in folder plugins is not a JarFile");
                } else {
                    addURL(file.toURI().toURL());
                    JarFile jarFile = new JarFile(file);
                    loadClasses(jarFile);
                    for (JarEntry entry : jarFile.stream().toList()) System.out.println(entry.getName());

                }
            } catch (InvalidPluginException | IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void loadClasses(JarFile jarFile) {
        jarFile.stream(). //Load all the classes in the plugin
                filter(e -> e.getName().endsWith(".class")).
                map(je -> je.getName().replace("/", ".").replace(".class", "")).
                toList().
                forEach(str -> {
                    try {
                        loadClass(str);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });
    }

    public String getFileExtension(File file) {
        String name = file.getName();
        int index = name.lastIndexOf(".");
        if (index == -1) {
            return "";
        }
        return name.substring(index);
    }
}
