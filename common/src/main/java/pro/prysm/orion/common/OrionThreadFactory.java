package pro.prysm.orion.common;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadFactory;

public class OrionThreadFactory implements ThreadFactory {
    private final Thread.UncaughtExceptionHandler exceptionHandler;
    private final String name;
    private int count;

    public OrionThreadFactory(String name) {
        this.exceptionHandler = OrionExceptionHandler.threadExceptionHandler;
        this.name = name;
        count = 0;
    }

    @Override
    public Thread newThread(@NotNull Runnable runnable) {
        count++;
        String threadName = String.format("%s #%d", name, count);
        AbstractOrion.getLogger().debug("Created new thread: {}", threadName);
        Thread thread = new Thread(runnable, threadName);
        thread.setUncaughtExceptionHandler(exceptionHandler);
        return thread;
    }

    public static Thread singleThread(String name, @NotNull Runnable runnable) {
        AbstractOrion.getLogger().debug("Created new thread: {}", name);
        Thread thread = new Thread(runnable, name);
        thread.setUncaughtExceptionHandler(OrionExceptionHandler.threadExceptionHandler);
        return thread;
    }
}
