package pro.prysm.orion.server.command;

/**
 * @author 254n_m
 * @since 12/18/21 / 8:37 PM
 * This file was created as a part of Orion
 */
public abstract class Command {
    private final String name;
    private final String description;

    protected Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void execute(String[] args, String name);

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
