package pro.prysm.orion.server.protocol.handler.play;

import lombok.Getter;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.event.event.PlayerMoveEvent;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.entity.player.ImplPlayer;
import pro.prysm.orion.server.protocol.outgoing.play.UpdateViewPosition;
import pro.prysm.orion.server.world.LevelManager;

import java.util.UUID;

public class Movement {
    private final ImplPlayer player;
    @Getter
    private UUID chunkTask;

    public Movement(ImplPlayer player) {
        this.player = player;
    }

    public void startChunkSending() {
        chunkTask = Orion.getScheduler().scheduleAtFixedRate(() -> {
            LevelManager levelManager = player.getConnection().getProtocol().getLevelManager();
            Location loc = player.getLocation();

            int baseX = (int) loc.getX() >> 4;
            int baseZ = (int) loc.getZ() >> 4;
            int halfDistance = player.getSettings().getViewDistance() / 2;

            int minX = baseX - halfDistance;
            int minZ = baseZ - halfDistance;
            int maxX = baseX + halfDistance;
            int maxZ = baseZ + halfDistance;

            for (int x = minX; x <= maxX; x++) {
                for (int z = minZ; z < maxZ; z++) {
                    player.sendChunkAsync(levelManager, x, z);
                }
            }
        }, 2L, 60L);
    }

    public Location playerMove(Location to, Location from) {
        // Every time the game sends a move packet, we should respond with the chunks around the player
        if (to.equals(from)) return from;   // TODO: this is always true

        PlayerMoveEvent event = new PlayerMoveEvent(player, to, player.getLocation());
        Orion.getEventBus().post(event);
        if (!event.isCancelled()) {
            // if (!event.getTo().equals(to)) // TODO: implement teleporting

            if ((int)to.getX() << 4 != (int) from.getX() << 4 | (int) to.getZ() << 4 != (int) from.getZ() << 4) {
                // A note about performance:
                // It is faster overall to perform the bit shift twice here rather than creating a variable for each
                // X and Y chunk coordinate since it is more likely that this if statement will be false than not.
                player.getConnection().sendPacket(new UpdateViewPosition((int)to.getX() << 4, (int) to.getZ() << 4));
            }

            return to;
        } else {
            // TODO: teleport player back to their original location

            return from;
        }
    }
}
