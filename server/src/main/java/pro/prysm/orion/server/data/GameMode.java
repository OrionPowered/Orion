package pro.prysm.orion.server.data;

public enum GameMode {
    SURVIVAL(0),
    CREATIVE(1),
    ADVENTURE(2),
    SPECTATOR(3),
    UNKNOWN(-1);

    private int id;
    GameMode(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
}
