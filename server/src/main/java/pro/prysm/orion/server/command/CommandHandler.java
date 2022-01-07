package pro.prysm.orion.server.command;

import pro.prysm.orion.server.Orion;

import java.util.HashMap;
import java.util.logging.Level;

/**
 * @author 254n_m
 * @since 12/18/21 / 8:30 PM
 * This file was created as a part of Orion
 */
public class CommandHandler {
    private final HashMap<String, Command> commands;

    public CommandHandler() {
        commands = new HashMap<>();
        CommandHandlerThread thread = new CommandHandlerThread(this);
        thread.start();
        thread.setName("CommandHandler");
    }

    public void registerCommand(Command command) {
        commands.put(command.getName(), command);
    }

    public void handleCommand(String name, String[] args) {
        synchronized (this) {
            if (commands.containsKey(name)) {
                Command command = commands.get(name);
                command.execute(args, name);
            } else {
                Orion.getLogger().warn("Unknown command. Type \"/help\" for help.");
            }
        }
    }
}
