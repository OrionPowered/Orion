package pro.prysm.orion.server.message;

import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import pro.prysm.orion.api.entity.player.Player;
import pro.prysm.orion.api.message.ChatFormatter;
import pro.prysm.orion.server.Orion;


public class DefaultChatFormatter implements ChatFormatter {

    @Override
    public Component format(Identity source, Component message) {
        Player sourcePlayer = Orion.getServer().getPlayer(source.uuid()).orElseThrow();
        return Component.text("<")
                .append(sourcePlayer.getDisplayName())
                .append(Component.text("> "))
                .append(message);
    }
}
