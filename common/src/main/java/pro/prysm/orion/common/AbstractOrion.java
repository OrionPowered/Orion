package pro.prysm.orion.common;

import ch.qos.logback.classic.Logger;

public class AbstractOrion {
    private static final long startupTime = System.currentTimeMillis();
    private static Logger logger;

    public static Logger getLogger() {
        return logger;
    }

    public static long getStartupTime() {
        return startupTime;
    }
}
