package pro.prysm.orion.server.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class Logger extends java.util.logging.Logger {
    /**
     * Creates a new Logger instance
     * @param name Logger name
     */
    public Logger(String name, Level level) {
        super(name, null);
        this.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(level);
        handler.setFormatter(new ConsoleFormat());
        this.addHandler(handler);
        setLevel(level);
    }

    public void debug(String msg) {
        log(Level.FINE, msg);
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
        Level level = record.getLevel();
        String format = "";
        if (level == Level.SEVERE) format = format(LogColor.RED, record);
        else if (level == Level.WARNING) format = format(LogColor.YELLOW, record);
        else if (level == Level.INFO) format = format(LogColor.WHITE, record);
        else if (level == Level.FINE) format = format(LogColor.BLUE, record);
        else if (level == Level.FINER) format = format(LogColor.GREEN, record);
        else format(LogColor.WHITE, record);
        return format;
    }

    private String format(LogColor color, LogRecord record) {
        StringBuilder builder = new StringBuilder();
        builder.append(color).append(df.format(new Date(record.getMillis()))).append(" ");
        builder.append("[").append(record.getLoggerName()).append("/").append(Thread.currentThread().getName()).append("/").append(record.getLevel()).append("]: ");
        builder.append(formatMessage(record));
        builder.append(LogColor.RESET);
        builder.append("\n");
        return builder.toString();
    }

}