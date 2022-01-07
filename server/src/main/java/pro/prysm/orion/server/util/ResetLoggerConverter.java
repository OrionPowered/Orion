package pro.prysm.orion.server.util;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class ResetLoggerConverter extends MessageConverter {
    @Override
    public String convert(ILoggingEvent event) {
        return LogColor.RESET.toString();
    }
}
