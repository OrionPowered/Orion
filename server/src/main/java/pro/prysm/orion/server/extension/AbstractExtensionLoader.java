package pro.prysm.orion.server.extension;

import pro.prysm.orion.api.extension.AbstractExtension;
import pro.prysm.orion.server.Orion;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractExtensionLoader {
    protected final List<AbstractExtension> extensions = new ArrayList<>();
    protected final File extensionFolder;
    protected final AbstractClassLoader loader;

    public AbstractExtensionLoader(File extensionFolder, AbstractClassLoader loader) {
        this.extensionFolder = extensionFolder;
        this.loader = loader;
        loadExtensions();
    }

    private void loadExtensions() {
        Orion.getLogger().info("{} loading...", this.getClass().getSimpleName());

        if (!extensionFolder.exists()) {
            //noinspection ResultOfMethodCallIgnored
            extensionFolder.mkdirs();
        }

        File[] extensionFiles = extensionFolder.listFiles();
        if (extensionFiles != null) {
            FilenameFilter filter = (dir, name) -> name.endsWith(".jar");
            List<File> jars = new ArrayList<>();
            for (File file : extensionFiles) {
                if (!file.isDirectory() && filter.accept(file.getParentFile(), file.getName())) {
                    jars.add(file);
                }
            }
            loader.loadExtensions(this, jars.toArray(File[]::new));
            onFinish();
        }
    }

    public abstract void onFinish();

}