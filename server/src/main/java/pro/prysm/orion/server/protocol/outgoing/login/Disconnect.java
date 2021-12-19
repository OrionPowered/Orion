package pro.prysm.orion.server.protocol.outgoing.login;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.api.chat.Message;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

public class Disconnect extends OutgoingPacket {
    private final Message message;
    public Disconnect(String message) {
        id = 0x00;
        this.message = new Message(message);
    }
    @Override
    public void write(ByteBuf buf) {
        writeString(message.toJsonString(), buf);
    }
}
