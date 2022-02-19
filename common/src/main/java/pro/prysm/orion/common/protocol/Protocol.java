package pro.prysm.orion.common.protocol;

import lombok.Getter;
import pro.prysm.orion.api.entity.player.Player;
import pro.prysm.orion.api.protocol.status.ServerListResponse;
import pro.prysm.orion.common.net.Connection;
import pro.prysm.orion.common.protocol.auth.AuthenticationProvider;
import pro.prysm.orion.common.protocol.outgoing.OutgoingPacket;

import java.util.List;

@Getter
public class Protocol {
    public static final int PROTOCOL_NUMBER = 757; // 1.18.1
    private final PacketRegistry packetRegistry;
    private final CipherSuite cipherSuite;
    private ServerListResponse slp = new ServerListResponse();
    private AuthenticationProvider auth;

    public Protocol() {
        cipherSuite = new CipherSuite();
        packetRegistry = new PacketRegistry();
        reload();
    }

    public void reload() {
        slp.getVersion().setProtocol(PROTOCOL_NUMBER);

//        slpData.getVersion().setName(server.getName());
//        slpData.setDescription(server.getMotdComponent());
//        slpData.getPlayers().setMax(server.getMaxPlayers());
//
//        try (InputStream is = getClass().getClassLoader().getResourceAsStream("prysm.png")) {
//            if (is == null)
//                return;
//            slpData.setFavicon(ServerListResponse.generateFavicon(is));
//        } catch (IOException e) {
//            ExceptionHandler.error(e);
//        }

    }

    public void broadcastPacket(List<Player> players, OutgoingPacket packet) {
        players.parallelStream().map(Player::getConnection).forEach(connection -> ((Connection) connection).sendPacket(packet));
    }
}
