package pro.prysm.orion.common.command.commands;

import pro.prysm.orion.api.event.event.ReloadEvent;
import pro.prysm.orion.common.AbstractOrion;
import pro.prysm.orion.common.command.Command;

public class ReloadCommand extends Command {
    public ReloadCommand() {
        super("reload", "Reloads Orion");
    }

    @Override
    public void execute(String[] args, String name) {
        AbstractOrion.getLogger().info("Reloading...");
        AbstractOrion.getEventBus().post(new ReloadEvent());
        AbstractOrion.getLogger().info("Orion reloaded!");
    }
}
