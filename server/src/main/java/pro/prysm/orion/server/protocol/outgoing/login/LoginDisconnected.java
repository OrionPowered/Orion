package pro.prysm.orion.server.protocol.outgoing.login;

import pro.prysm.orion.api.chat.Message;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

public class LoginDisconnected extends OutgoingPacket implements pro.prysm.orion.api.protocol.outgoing.login.Disconnect {
    private final Message message;

    public LoginDisconnected(String message) {
        id = 0x00;
        this.message = new Message(message);
    }

    @Override
    public Message getMessage() {
        return message;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeString(message.toJsonString());
    }
}
