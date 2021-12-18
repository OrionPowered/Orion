package pro.prysm.orion.server.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class Logger extends java.util.logging.Logger {
    /**
     * Creates a new Logger instance
     * @param name Logger name
     */
    public Logger(String name) {
        super(name, null);
        this.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new ConsoleFormat());
        this.addHandler(handler);
    }
}

class ConsoleFormat extends Formatter {
    private final DateFormat df;

    /**
     * Constructor
     */
    public ConsoleFormat() {
        df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss.SSS");
    }

    /**
     * Creates format
     * @param record LogRecord
     * @return String
     */
    @Override
    public String format(LogRecord record) {
        StringBuilder builder = new StringBuilder();
        Level level = record.getLevel();
        boolean levelKnown = false;
        if (level == Level.SEVERE) {
            builder.append(LogColor.RED).append("[").append(df.format(new Date(record.getMillis()))).append("] ");
            builder.append("[").append(record.getLoggerName()).append(" - ").append(record.getLevel()).append("]: ");
            levelKnown = true;
        } else if (level == Level.WARNING) {
            builder.append(LogColor.YELLOW).append("[").append(df.format(new Date(record.getMillis()))).append("] ");
            builder.append("[").append(record.getLoggerName()).append(" - ").append(record.getLevel()).append("]: ");
            levelKnown = true;
        } else if (level == Level.INFO) {
            builder.append(LogColor.WHITE).append("[").append(df.format(new Date(record.getMillis()))).append("] ");
            builder.append("[").append(record.getLoggerName()).append(" - ").append(record.getLevel()).append("]: ");
            levelKnown = true;
        }
        if (!levelKnown) {
            builder.append(LogColor.WHITE).append("[").append(df.format(new Date(record.getMillis()))).append("] ");
            builder.append("[").append(record.getLoggerName()).append(" - ").append(record.getLevel()).append("]: ");
        }
        builder.append(formatMessage(record));
        builder.append(LogColor.RESET);
        builder.append("\n");
        return builder.toString();
    }
}