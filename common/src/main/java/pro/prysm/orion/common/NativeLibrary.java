package pro.prysm.orion.common;

import pro.prysm.orion.api.exception.ResourceNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class NativeLibrary {
    private final String name;

    public NativeLibrary(String name) {
        this.name = System.mapLibraryName("orion_" + name);
    }

    public void load() throws ResourceNotFoundException {
        File libDir = new File("lib");
        if (!libDir.exists()) libDir.mkdirs();
        File lib = new File(libDir, name);

        try {
            if (!lib.exists() || (lib.exists() && !Arrays.equals(getDigest(getResource(name)), getDigest(new FileInputStream(lib))))) {
                replaceFromJar(lib, getResource(name));
            }
        } catch (NoSuchAlgorithmException | IOException e) {
            OrionExceptionHandler.error(e);
        }
        System.load(lib.getAbsolutePath());
    }

    private InputStream getResource(String name) throws ResourceNotFoundException {
        InputStream is = NativeLibrary.class.getClassLoader().getResourceAsStream("META-INF/native/" + name);
        if (is == null) throw new ResourceNotFoundException("Could not find lib " + name + " in jar");
        return is;
    }

    private void replaceFromJar(File output, InputStream is) throws IOException {
        Files.copy(is, output.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    private byte[] getDigest(InputStream is) throws IOException, NoSuchAlgorithmException {
        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("MD5");
        int numRead = 0;

        while (numRead != -1) {
            numRead = is.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        }

        is.close();
        return complete.digest();
    }
}
