package pro.prysm.orion.common.extension;

import lombok.Getter;
import org.slf4j.LoggerFactory;
import pro.prysm.orion.api.exception.InvalidExtensionDescription;
import pro.prysm.orion.api.extension.AbstractExtension;
import pro.prysm.orion.api.extension.ExtensionDescription;
import pro.prysm.orion.common.AbstractOrion;
import pro.prysm.orion.common.OrionExceptionHandler;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

@Getter
public abstract class AbstractExtensionLoader extends AbstractClassLoader {
    protected final List<AbstractExtension> extensions = new ArrayList<>();
    protected final File extensionFolder;
    private final String configFile;

    protected Field dataFolderField;
    protected Field loggerField;
    protected Field descriptionField;
    protected Field eventBusField;

    public AbstractExtensionLoader(URL[] urls, ClassLoader parent, File extensionFolder, String configFile) {
        super(urls, parent);
        this.extensionFolder = extensionFolder;
        this.configFile = configFile;

        try {
            dataFolderField = AbstractExtension.class.getDeclaredField("dataFolder");
            dataFolderField.setAccessible(true);

            loggerField = AbstractExtension.class.getDeclaredField("logger");
            loggerField.setAccessible(true);

            descriptionField = AbstractExtension.class.getDeclaredField("description");
            descriptionField.setAccessible(true);

            eventBusField = AbstractExtension.class.getDeclaredField("eventBus");
            eventBusField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            OrionExceptionHandler.error(e);
        }

        loadExtensions();
    }

    private void loadExtensions() {
        AbstractOrion.getLogger().info("{} loading...", this.getClass().getSimpleName());

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

            File[] jarFiles = jars.toArray(File[]::new);

            for (File file : jarFiles) {
                try {
                    addURL(file.toURI().toURL());
                    JarFile jarFile = new JarFile(file);
                    loadClasses(jarFile);

                    ExtensionDescription description = new ExtensionDescription(jarFile, configFile);

                    AbstractExtension extension = (AbstractExtension) Class.forName(description.getMainClass(), false, this)
                            .getConstructors()[0].newInstance();

                    dataFolderField.set(extension, Path.of(extensionFolder.getPath(), description.getName()));
                    loggerField.set(extension, LoggerFactory.getLogger(description.getName()));
                    descriptionField.set(extension, description);
                    eventBusField.set(extension, AbstractOrion.getEventBus());

                    initializeExtension(extension);

                    extensions.add(extension);
                    AbstractOrion.getLogger().info("Enabling {} by {}", description.getName(), description.getAuthor());
                    extension.generateConfig();
                    extension.onEnable();
                    AbstractOrion.getLogger().info("{} enabled", description.getName());
                } catch (IOException | ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | InvalidExtensionDescription e) {
                    OrionExceptionHandler.error(e);
                }
            }
            onFinish();
        }
    }

    public abstract void initializeExtension(AbstractExtension extension) throws IllegalAccessException;

    public abstract void onFinish();
}
