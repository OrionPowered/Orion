package pro.prysm.orion.server.util;

import pro.prysm.orion.server.Orion;

public class ExceptionHandler {
    public static Thread.UncaughtExceptionHandler threadExceptionHandler = new ThreadUncaughtException();

    public static void error(String msg, Throwable cause) {
        Orion.getLogger().error("Orion has encountered an exception: {}", msg, cause);
    }

    public static void error(Throwable cause) {
        error("", cause);
    }
}

class ThreadUncaughtException implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        ExceptionHandler.error(throwable);
    }
}