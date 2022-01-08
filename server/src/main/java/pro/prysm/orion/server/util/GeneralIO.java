package pro.prysm.orion.server.util;

import java.nio.file.Path;

// Move to api?
public class GeneralIO {
    public static boolean fileExists(Path path) {
        return path.toFile().exists();
    }
}
