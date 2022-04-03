package pro.prysm.orion.server.protocol.handler.play;

import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.event.event.PlayerMoveEvent;
import pro.prysm.orion.common.protocol.outgoing.play.UpdateViewPosition;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.entity.player.ImplPlayer;
import pro.prysm.orion.server.math.Velocity;

public class Movement {
    private final ImplPlayer player;

    private final float sprintingMax = 0.74F;

    public Movement(ImplPlayer player) {
        this.player = player;
    }

    private boolean validMovement(Velocity velocity) {
        double distance = velocity.distance();
        if (player.isSprinting()) {
            if (player.isFlying()) return true; // TODO
            if (player.isFalling()) return true; // TODO
            return !(distance > sprintingMax);
        }
        return true;
    }

    public Location playerMove(Location to, Location from) {
        if (to.equals(from)) {
            player.setVelocity(0F, 0F, 0F);
            return from;
        } else {
            Velocity velocity = player.getVelocity();
            velocity.move(to);

            if (!validMovement(velocity)) {
                Orion.getLogger().warn("Player {}/{} moved incorrectly! {}", player.getProfile().getUsername(), player.uuid(), to);
                player.teleport(from);
                return from;
            }

            PlayerMoveEvent event = new PlayerMoveEvent(player, to, player.getLocation());
            Orion.getEventBus().post(event);

            if (!player.getWorld().isVoid() && !event.isCancelled()) {
                if (!to.isSameChunk(from)) {
                    player.getConnection().sendPacket(new UpdateViewPosition(to.getChunkX(), to.getChunkZ()));
                    ((PlayHandler) player.getConnection().getHandler()).sendChunks(to);
                }
                return to;
            } else {
                player.teleport(from);
                return from;
            }
        }


    }
}
