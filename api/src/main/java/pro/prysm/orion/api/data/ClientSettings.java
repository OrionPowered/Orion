package pro.prysm.orion.api.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ClientSettings {
    private String locale;
    private int viewDistance;
    private ChatMode chatMode;
    private boolean coloredChat;
    private short displayedSkinParts;
    private Hand mainHand;
    // private final boolean textFiltering; (this is always false as filtering is always disabled by the client as of 757)
    // private final boolean showInList; (The client can ask to hide itself from the server list, but this is not good, so we ignore it)
}
