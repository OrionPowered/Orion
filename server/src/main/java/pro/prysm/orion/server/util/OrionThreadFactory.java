package pro.prysm.orion.server.util;

import org.jetbrains.annotations.NotNull;
import pro.prysm.orion.server.Orion;

import java.util.concurrent.ThreadFactory;

public class OrionThreadFactory implements ThreadFactory {
    private final Thread.UncaughtExceptionHandler exceptionHandler;
    private final String name;
    private int count;

    public OrionThreadFactory(String name) {
        this.exceptionHandler = new ThreadExceptionHandler();
        this.name = name;
        count = 0;
    }

    @Override
    public Thread newThread(@NotNull Runnable runnable) {
        count++;
        String threadName = String.format("%s #%d", name, count);
        Orion.getLogger().debug("Created new thread: {}", threadName);
        Thread thread = new Thread(runnable, threadName);
        thread.setUncaughtExceptionHandler(exceptionHandler);
        return thread;
    }


    class ThreadExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread thread, Throwable throwable) {
            Orion.getLogger().error("Thread \"{}\" encountered an exception:", thread.getName());
            throwable.printStackTrace();
        }
    }
}
