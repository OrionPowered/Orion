package pro.prysm.orion.common;

public class OrionExceptionHandler {
    public static Thread.UncaughtExceptionHandler threadExceptionHandler = new ThreadUncaughtException();

    public static void error(String msg, Throwable cause) {
        AbstractOrion.getLogger().error("Orion has encountered an exception: {}", msg, cause);
    }

    public static void error(Throwable cause) {
        error("", cause);
    }
}
class ThreadUncaughtException implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        OrionExceptionHandler.error(throwable);
    }
}