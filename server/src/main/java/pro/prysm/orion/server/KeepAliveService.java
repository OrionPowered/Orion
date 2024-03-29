package pro.prysm.orion.server;

import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.common.scheduler.OrionScheduler;
import pro.prysm.orion.common.net.Connection;
import pro.prysm.orion.server.protocol.handler.play.PlayHandler;

public class KeepAliveService {
    public KeepAliveService() {
        Orion.getScheduler().scheduleAtFixedRate(() -> {
            Orion.getServer().getPlayers().parallelStream().forEach(player -> {
                Connection connection = (Connection) player.getConnection();
                if (connection.getState() == PacketState.PLAY && connection.isActive()) {
                    ((PlayHandler) connection.getHandler()).sendKeepAlive();
                }
            });
        }, 10L, (25 * OrionScheduler.TPS)); // Every 25 seconds (30 seconds resulted in random timeouts)
    }
}
