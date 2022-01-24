package pro.prysm.orion.server.world.dimension;

public enum Dimension {
    OVERWORLD_CAVES("minecraft:overworld_caves"),
    OVERWORLD("minecraft:overworld"),
    NETHER("minecraft:the_nether"),
    END("minecraft:the_end");

    private final String name;

    Dimension(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
