package pro.prysm.orion.server.command.commands;

import pro.prysm.orion.api.event.event.ReloadEvent;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.command.Command;

public class ReloadCommand extends Command {
    public ReloadCommand() {
        super("reload", "Reloads Orion");
    }

    @Override
    public void execute(String[] args, String name) {
        Orion.getLogger().info("Reloading...");
        Orion.getEventBus().post(new ReloadEvent());
        Orion.getLogger().info("Orion reloaded!");
    }
}
