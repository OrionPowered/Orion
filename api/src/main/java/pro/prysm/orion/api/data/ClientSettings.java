package pro.prysm.orion.api.data;

public class ClientSettings {
    private String locale;
    private int viewDistance;
    private ChatMode chatMode;
    private boolean coloredChat;
    private short displayedSkinParts;
    private Hand mainHand;
    // private final boolean textFiltering; (this is always false as filtering is always disabled by the client as of 757)
    // private final boolean showInList; (The client can ask to hide itself from the server list, but this is not good, so we ignore it)

    public ClientSettings(String locale, int viewDistance, ChatMode chatMode, boolean coloredChat, short displayedSkinParts, Hand mainHand) {
        this.locale = locale;
        this.viewDistance = viewDistance;
        this.chatMode = chatMode;
        this.coloredChat = coloredChat;
        this.displayedSkinParts = displayedSkinParts;
        this.mainHand = mainHand;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public int getViewDistance() {
        return viewDistance;
    }

    public void setViewDistance(int viewDistance) {
        this.viewDistance = viewDistance;
    }

    public ChatMode getChatMode() {
        return chatMode;
    }

    public short getDisplayedSkinParts() {
        return displayedSkinParts;
    }

    public void setChatMode(ChatMode chatMode) {
        this.chatMode = chatMode;
    }

    public boolean isColoredChat() {
        return coloredChat;
    }

    public void setColoredChat(boolean coloredChat) {
        this.coloredChat = coloredChat;
    }

    public Hand getMainHand() {
        return mainHand;
    }

    public void setMainHand(Hand mainHand) {
        this.mainHand = mainHand;
    }
}
