package pro.prysm.orion.server.protocol.handler.play;

import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.event.event.PlayerMoveEvent;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.entity.player.ImplPlayer;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.outgoing.play.ChunkData;
import pro.prysm.orion.server.world.LevelManager;

public class Movement {
    private final ImplPlayer player;
    public Movement(ImplPlayer player) {
        this.player = player;
    }

    public void sendChunks() {
        Connection connection = player.getConnection();
        LevelManager levelManager = connection.getProtocol().getLevelManager();
        // Player has joined, send first chunk

        // NOTE: This is a really bad implementation just to confirm loading multiple chunks works
        // I will fix later - Alex
        Location loc = player.getLocation();
        connection.sendPacket(new ChunkData(levelManager.getChunk(loc)));
        loc.addX(16);
        connection.sendPacket(new ChunkData(levelManager.getChunk(loc)));
        loc.addZ(16);
        connection.sendPacket(new ChunkData(levelManager.getChunk(loc)));
        loc.subX(32);
        connection.sendPacket(new ChunkData(levelManager.getChunk(loc)));
        loc.subZ(32);
        connection.sendPacket(new ChunkData(levelManager.getChunk(loc)));
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
