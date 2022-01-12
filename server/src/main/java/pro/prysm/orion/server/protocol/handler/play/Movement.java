package pro.prysm.orion.server.protocol.handler.play;

import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.event.event.PlayerMoveEvent;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.entity.player.ImplPlayer;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.world.LevelManager;

public class Movement {
    private final ImplPlayer player;
    public Movement(ImplPlayer player) {
        this.player = player;
    }

    public void sendChunks() {
        Connection connection = player.getConnection();
        LevelManager levelManager = connection.getProtocol().getLevelManager();
        Location loc = player.getLocation();

        int baseX = (int) loc.getX() >> 4;
        int baseZ = (int) loc.getZ() >> 4;
        int halfDistance = player.getSettings().getViewDistance()/2;
        player.sendChunks(levelManager.getChunks(baseX-halfDistance, baseZ-halfDistance, baseX+halfDistance, baseZ+halfDistance));
    }

    public Location playerMove(Location to, Location from) {
        // Every time the game sends a move packet, we should respond with the chunks around the player
        sendChunks();
        if (to.equals(from)) return from;

        PlayerMoveEvent event = new PlayerMoveEvent(player, to, player.getLocation());
        Orion.getEventBus().post(event);
        if (!event.isCancelled()) {
            // if (!event.getTo().equals(to)) // TODO: implement teleporting
            return to;
        } else {
            // TODO: teleport player back to their original location
            return from;
        }
    }
}
