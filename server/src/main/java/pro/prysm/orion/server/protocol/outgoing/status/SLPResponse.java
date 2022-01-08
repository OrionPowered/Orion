package pro.prysm.orion.server.protocol.outgoing.status;

import lombok.Getter;
import lombok.Setter;
import pro.prysm.orion.api.protocol.status.ServerListResponse;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

@Getter
@Setter
public class SLPResponse extends OutgoingPacket implements pro.prysm.orion.api.protocol.outgoing.status.SLPResponse {
    private ServerListResponse response;

    public SLPResponse() {
        super(0x00);
    }

    public SLPResponse(ServerListResponse response) {
        this();
        this.response = response;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeString(response.toJsonString());
    }
}
