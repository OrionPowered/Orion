package pro.prysm.orion.server.message;

public enum ChatPosition {
    CHAT(0),
    SYSTEM(1),
    GAME_INFO(2); // This is the action bar

    private final int value;

    ChatPosition(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
