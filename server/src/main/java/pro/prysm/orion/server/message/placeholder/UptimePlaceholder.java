package pro.prysm.orion.server.message.placeholder;

import pro.prysm.orion.api.message.Placeholder;
import pro.prysm.orion.server.Orion;

public class UptimePlaceholder implements Placeholder<String> {
    @Override
    public String call() {
        long elapsed = System.currentTimeMillis() - Orion.getStartupTime();

        long x = elapsed / 1000;
        long seconds = x % 60;
        x /= 60;
        long minutes = x % 60;
        x /= 60;
        long hours = x % 24;

        if (minutes == 0) {
            return String.format("%ds", seconds);
        } else if (hours == 0) {
            return String.format("%dm %ds", minutes, seconds);
        } else return String.format("%dh %dm %ds", hours, minutes, seconds);
    }
}

