package pro.prysm.orion.server.protocol.outgoing.status;

import io.netty.buffer.ByteBuf;

import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

public class SLPResponse extends OutgoingPacket {
    private String response;
    public SLPResponse() {
        id = 0x00;
        response = "{\"version\": {\"name\": \"Orion\", \"protocol\": 757}, \"description\": {\"text\": \"Test Server\"}}";
    }

    @Override
    public void write(ByteBuf buf) {
        writeString(response, buf);
    }
}
