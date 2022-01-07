package pro.prysm.orion.api.chat;

import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

@RequiredArgsConstructor
public class Message {
    private final Component component;

    public Message(String msg) {
        this(MiniMessage.get().parse(msg));
    }

    public Component toComponent() {
        return component;
    }

    public String toJsonString() {
        return GsonComponentSerializer.gson().serialize(component);
    }
}
