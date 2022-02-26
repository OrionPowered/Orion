package pro.prysm.orion.server.command;

import pro.prysm.orion.server.Orion;
import pro.prysm.orion.common.command.Command;

public class UptimeCommand extends Command {
    public UptimeCommand() {
        super("uptime", "Returns Orion's uptime");
    }

    @Override
    public void execute(String[] args, String name) {
        // This was created to test the placeholder service. I know this could be done
        // without using the placeholder service. - Alex
        Orion.getLogger().info(Orion.getServer().getPlaceholderService().resolve("Uptime: %uptime%"));
    }
}
