package pro.prysm.orion.api.chat;

import net.kyori.adventure.text.minimessage.MiniMessage;

public class Chat {
    public static MiniMessage miniMessage() {
        return MiniMessage.get();
    }
}