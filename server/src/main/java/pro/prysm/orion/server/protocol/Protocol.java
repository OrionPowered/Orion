package pro.prysm.orion.server.protocol;

import lombok.Getter;
import pro.prysm.orion.api.entity.player.Player;
import pro.prysm.orion.api.protocol.status.ServerListResponse;
import pro.prysm.orion.common.net.Connection;
import pro.prysm.orion.common.protocol.outgoing.OutgoingPacket;
import pro.prysm.orion.common.protocol.outgoing.login.EncryptionRequest;
import pro.prysm.orion.common.protocol.outgoing.status.SLPResponse;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.Server;
import pro.prysm.orion.server.util.ExceptionHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public class Protocol {
    public static final int PROTOCOL_NUMBER = 757; // 1.18.1

    private final PacketRegistry packetRegistry = new PacketRegistry();
    private final ServerListResponse slpData = new ServerListResponse();

    public Protocol(Server server) {
        reload(server);
    }

    public void reload(Server server) {
        slpData.getVersion().setProtocol(PROTOCOL_NUMBER);
        slpData.getVersion().setName(server.getName());
        slpData.setDescription(server.getMotdComponent());
        slpData.getPlayers().setMax(server.getMaxPlayers());

        try (InputStream is = getClass().getClassLoader().getResourceAsStream("prysm.png")) {
            if (is == null)
                return;
            slpData.setFavicon(ServerListResponse.generateFavicon(is));
        } catch (IOException e) {
            ExceptionHandler.error(e);
        }
        if (!server.isOnlineMode())
            Orion.getLogger().warn("Orion is running in offline mode. Players will not be authenticated!");
        else Orion.getLogger().debug("Using session server {}", server.getSessionServer());
    }

    public SLPResponse generateSLP() {
        ServerListResponse slp = new ServerListResponse();

        slp.getVersion().setProtocol(slpData.getVersion().getProtocol());
        slp.getVersion().setName(slpData.getVersion().getName());
        slp.getPlayers().setMax(slpData.getPlayers().getMax());
        slpData.getPlayers().setOnline(Orion.getServer().getPlayers().size());
        slp.setDescription(slpData.getDescription());

        return new SLPResponse(slpData);
    }

    public EncryptionRequest newEncryptionRequest() {
        byte[] verifyToken = new byte[4];
        ThreadLocalRandom.current().nextBytes(verifyToken);
        return new EncryptionRequest(keyPair.getPublic().getEncoded(), verifyToken);
    }

    public void broadcastPacket(List<Player> players, OutgoingPacket packet) {
        players.parallelStream().map(Player::getConnection).forEach(connection -> ((Connection) connection).sendPacket(packet));
    }
}
