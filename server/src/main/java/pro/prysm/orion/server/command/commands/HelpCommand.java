package pro.prysm.orion.server.command.commands;

import pro.prysm.orion.server.command.Command;

/**
 * @author 254n_m
 * @since 12/18/21 / 8:52 PM
 * This file was created as a part of Orion
 */
public class HelpCommand extends Command {
    public HelpCommand() {
        super("help", "Provides a help menu");
    }

    @Override
    public void execute(String[] args, String name) {

    }
}
