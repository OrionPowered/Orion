package pro.prysm.orion.server.protocol;

import pro.prysm.orion.api.JSONConfig;
import pro.prysm.orion.api.chat.Message;
import pro.prysm.orion.api.protocol.ServerListResponse;
import pro.prysm.orion.server.protocol.outgoing.status.SLPResponse;

public class Protocol {
    private final PacketRegistry packetRegistry;
    private ServerListResponse defaultSLPResponse;
    private int maxPlayers;

    public Protocol(JSONConfig config) {
        packetRegistry = new PacketRegistry();
        defaultSLPResponse = new ServerListResponse();
        reload(config);
    }

    public void reload(JSONConfig config) {
        maxPlayers = config.getInt("max-players");
        defaultSLPResponse.setProtocolVersion(757); // 1.18.1
        defaultSLPResponse.setDescription(new Message(config.getString("motd")).toComponent());
        defaultSLPResponse.setServerName(config.getString("serverName"));
        defaultSLPResponse.setMaxPlayers(maxPlayers);
    }

    public PacketRegistry getPacketRegistry() {
        return packetRegistry;
    }

    public void setDefaultMOTD(ServerListResponse response) {
        this.defaultSLPResponse =  response;
    }

    public void getMOTDfromConfig(JSONConfig config) {

    }

    public SLPResponse getDefaultSLP() {
        defaultSLPResponse.setOnlinePlayers(0); // TODO: implement online players
        return new SLPResponse(this, defaultSLPResponse);
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }
}
