package pro.prysm.orion.server.protocol.outgoing.login;

import lombok.Getter;
import pro.prysm.orion.api.chat.Message;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

@Getter
public class LoginDisconnected extends OutgoingPacket {
    private final Message message;

    public LoginDisconnected(String message) {
        super(0x00);
        this.message = new Message(message);
    }

    public LoginDisconnected(int id, String message) {
        super(0x00);
        this.message = new Message(message);
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeString(message.toJsonString());
    }
}
