package pro.prysm.orion.common.logger;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class ColorLoggerConverter extends MessageConverter {
    @Override
    public String convert(ILoggingEvent event) {
        return enhance(event).toString();
    }

    private LogColor enhance(ILoggingEvent event) {
        Level level = event.getLevel();
        if (level == Level.ERROR) return LogColor.RED;
        else if (level == Level.WARN) return LogColor.YELLOW;
        else if (level == Level.INFO) return LogColor.WHITE;
        else if (level == Level.DEBUG) return LogColor.BLUE;
        else if (level == Level.TRACE) return LogColor.GREEN;
        else return LogColor.WHITE;
    }
}
