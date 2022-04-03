package pro.prysm.orion.server;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.common.AbstractOrion;
import pro.prysm.orion.common.event.EventBus;
import pro.prysm.orion.common.protocol.Protocol;
import pro.prysm.orion.common.protocol.incoming.play.*;
import pro.prysm.orion.common.scheduler.OrionScheduler;
import pro.prysm.orion.server.protocol.bidirectional.PlayerAbilities;
import pro.prysm.orion.server.protocol.handler.HandshakeHandler;
import pro.prysm.orion.server.protocol.incoming.*;

public class Orion extends AbstractOrion {
    private static Server server;

    public static void main(String[] args) {
        logger = (Logger) LoggerFactory.getLogger("Orion");
        logger.info("Starting Orion...");
        scheduler = new OrionScheduler();
        eventBus = new EventBus();
        protocol = new Protocol();
        protocol.setDefaultHandlerClass(HandshakeHandler.class);
        protocol.getPacketRegistry()
                .registerIncoming(PacketState.PLAY, 0x00, TeleportConfirm.class)
                .registerIncoming(PacketState.PLAY, 0x03, ChatMessageIn.class)
                .registerIncoming(PacketState.PLAY, 0x05, ClientSettings.class)
                .registerIncoming(PacketState.PLAY, 0x0A, PluginMessageIn.class)
                .registerIncoming(PacketState.PLAY, 0x11, PlayerPosition.class)
                .registerIncoming(PacketState.PLAY, 0x12, PlayerPositionAndRotation.class)
                .registerIncoming(PacketState.PLAY, 0x13, PlayerRotation.class)
                .registerIncoming(PacketState.PLAY, 0x14, PlayerMovement.class)
                .registerIncoming(PacketState.PLAY, 0x19, PlayerAbilities.Incoming.class)
                .registerIncoming(PacketState.PLAY, 0x0F, KeepAliveIn.class)
                .registerIncoming(PacketState.PLAY, 0x30, Pong.class)
                .registerIncoming(PacketState.PLAY, 0x1B, EntityAction.class);
        server = new Server();
        IServer = server;
    }

    public static Server getServer() {
        return server;
    }

    public static long getStartupTime() {
        return startupTime;
    }
}
