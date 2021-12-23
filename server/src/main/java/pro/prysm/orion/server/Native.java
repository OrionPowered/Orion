package pro.prysm.orion.server;

import pro.prysm.orion.api.exception.ResourceNotFoundException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Native {

    /**
     * Loads a native library from the resources of this Jar
     * @param name Library to load (without extension)
     * @throws ResourceNotFoundException Library not found in resources
     * @throws IOException Cannot move library out of Jar
     */
    public static void load(String name) throws ResourceNotFoundException, IOException {
        String os = System.getProperty("os.name");
        if (os.contains("Linux")) name = name + ".so";
        else if (os.contains("Mac OS")) name = name + ".dylib";
        File libDir = new File("lib");
        if (!libDir.exists()) libDir.mkdirs();
        File object = new File("lib", name);
        // if (!object.exists()) {
            InputStream is = Orion.class.getClassLoader().getResourceAsStream(name);
            if (is == null) throw new ResourceNotFoundException("Could not find lib " + name + " in jar");
            Files.copy(is, object.toPath(), StandardCopyOption.REPLACE_EXISTING);
        // }
        System.load(object.getAbsolutePath());
    }
}
