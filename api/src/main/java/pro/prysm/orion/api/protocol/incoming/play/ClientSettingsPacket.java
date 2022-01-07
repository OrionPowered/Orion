package pro.prysm.orion.api.protocol.incoming.play;

public interface ClientSettingsPacket {
    String getLocale();

    int getViewDistance();

    byte getChatMode();

    boolean isColoredChat();

    short getSkinParts();

    int getMainHand();

    boolean isTextFiltering();

    boolean isShowInList();
}
