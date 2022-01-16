package pro.prysm.orion.server.command.commands;

import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.command.Command;

public class UptimeCommand extends Command {
    public UptimeCommand() {
        super("uptime", "Returns Orion's uptime");
    }

    @Override
    public void execute(String[] args, String name) {
        // This was created to test the placeholder service. I know this could be done
        // without using the placeholder service. - Alex
        Orion.getLogger().info(Orion.getPlaceholderService().resolve("Uptime: %uptime%"));
    }
}
