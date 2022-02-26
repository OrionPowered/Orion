package pro.prysm.test.plugin;

import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import pro.prysm.orion.api.Server;
import pro.prysm.orion.api.entity.player.Player;
import pro.prysm.orion.api.message.ChatFormatter;

public class ExampleChatFormatter implements ChatFormatter {
    private final Server server;
    public ExampleChatFormatter(Server server) {
        this.server = server;
    }

    @Override
    public Component format(Identity source, Component message) {
        Player sourcePlayer = server.getPlayer(source.uuid()).orElseThrow();
        return Component.text("<")
                .append(sourcePlayer.getDisplayName().color(TextColor.color(0x6EFF3B)))
                .append(Component.text("> "))
                .append(message.color(TextColor.color(0x6EFF3B)));
    }
}
