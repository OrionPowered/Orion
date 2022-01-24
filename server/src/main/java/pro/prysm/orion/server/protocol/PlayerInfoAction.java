package pro.prysm.orion.server.protocol;

public enum PlayerInfoAction {
    ADD_PLAYER(0),
    UPDATE_GAMEMODE(1),
    UPDATE_LATENCY(2),
    UPDATE_DISPLAY_NAME(3),
    REMOVE_PLAYER(4);

    private final int id;

    PlayerInfoAction(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
