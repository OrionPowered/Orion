package pro.prysm.orion.server.protocol.handler.play;

import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.event.event.PlayerMoveEvent;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.entity.player.ImplPlayer;
import pro.prysm.orion.server.protocol.outgoing.play.UpdateViewPosition;

public class Movement {
    private final ImplPlayer player;

    public Movement(ImplPlayer player) {
        this.player = player;
    }

    public Location playerMove(Location to, Location from) {
        if (to.equals(from)) return from;

        PlayerMoveEvent event = new PlayerMoveEvent(player, to, player.getLocation());
        Orion.getEventBus().post(event);
        if (!event.isCancelled()) {
            if (!to.isSameChunk(from))
                player.getConnection().sendPacket(new UpdateViewPosition(to.getChunkX(), to.getChunkZ()));

            return to;
        } else {
            player.teleport(from);
            return from;
        }
    }
}
