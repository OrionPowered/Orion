package pro.prysm.orion.common.protocol;

import lombok.Getter;
import lombok.Setter;
import pro.prysm.orion.api.entity.player.Player;
import pro.prysm.orion.api.protocol.status.ServerListResponse;
import pro.prysm.orion.common.OrionExceptionHandler;
import pro.prysm.orion.common.net.Connection;
import pro.prysm.orion.common.protocol.auth.AuthenticationProvider;
import pro.prysm.orion.common.protocol.outgoing.OutgoingPacket;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Getter
public class Protocol {
    public static final int PROTOCOL_NUMBER = 757; // 1.18.1
    private final PacketRegistry packetRegistry;
    private final CipherSuite cipherSuite;
    private final ServerListResponse slp = new ServerListResponse();
    @Setter
    private AuthenticationProvider auth;
    @Setter
    private Class<? extends Handler> defaultHandlerClass;

    public Protocol() {
        cipherSuite = new CipherSuite();
        packetRegistry = new PacketRegistry();
    }

    public Handler getDefaultHandler(Object ...initargs)  {
        Handler handler = null;
        try {
            handler = (Handler) defaultHandlerClass.getConstructors()[0].newInstance(initargs);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            OrionExceptionHandler.error("Failed to call constructor of default handler", e);
        }
        return handler;
    }

    public void broadcastPacket(List<Player> players, OutgoingPacket packet) {
        players.parallelStream().map(Player::getConnection).forEach(connection -> ((Connection) connection).sendPacket(packet));
    }
}
