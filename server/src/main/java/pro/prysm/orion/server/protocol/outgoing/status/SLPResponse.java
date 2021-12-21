package pro.prysm.orion.server.protocol.outgoing.status;

import io.netty.buffer.ByteBuf;

import pro.prysm.orion.api.protocol.ServerListResponse;
import pro.prysm.orion.server.protocol.Protocol;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

public class SLPResponse extends OutgoingPacket {
    private ServerListResponse response;

    public SLPResponse(Protocol protocol) {
        super(protocol);
        id = 0x00;
    }

    public SLPResponse(Protocol protocol, ServerListResponse response) {
        this(protocol);
        this.response = response;
    }

    public void setResponse(ServerListResponse response) {
        this.response = response;
    }

    public ServerListResponse getResponse() {
        return response;
    }


    @Override
    public void write(ByteBuf buf) {
        writeString(response.toJsonString(), buf);
    }
}
