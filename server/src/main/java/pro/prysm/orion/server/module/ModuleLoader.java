package pro.prysm.orion.server.module;

import lombok.Getter;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.module.api.JavaModule;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ModuleLoader {
    @Getter
    private final List<JavaModule> modules = new ArrayList<>();
    private final File moduleFolder = new File("./modules");
    private final ModuleClassLoader moduleClassLoader = new ModuleClassLoader(new URL[]{}, this.getClass().getClassLoader(), this);

    public void loadModules() {
        Orion.getLogger().info("Loading modules");

        if(!moduleFolder.exists()) {
            if(!moduleFolder.mkdir()) {
                Orion.getLogger().warn("Unable to create module folder, aborting module load!");
                return;
            }
        }

        File[] moduleFiles = moduleFolder.listFiles();
        if (moduleFiles == null) return;

        FilenameFilter filter = (dir, name) -> name.endsWith(".jar");
        List<File> jars = new ArrayList<>();
        for (File file : moduleFiles) {
            if (file.isDirectory()) continue;

            if (filter.accept(file.getParentFile(), file.getName())) {
                jars.add(file);
            }
        }

        moduleClassLoader.loadModules(jars.toArray(File[]::new));
    }
}
