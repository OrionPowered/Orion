package pro.prysm.test.listener;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import pro.prysm.orion.api.event.EventHandler;
import pro.prysm.orion.api.event.Listener;
import pro.prysm.orion.api.event.event.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendActionBar(Component.text("This is an action bar!"));
        event.getPlayer().sendMessage(Component.text("This server is running OrionTestPlugin").color(TextColor.fromHexString("#2fc1fa")));
    }
}
