package pro.prysm.orion.server.protocol.incoming.play;

import lombok.Getter;
import pro.prysm.orion.api.protocol.incoming.play.ChatMessagePacket;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

@Getter
public class ChatMessage extends IncomingPacket implements ChatMessagePacket {
    private String message;

    public ChatMessage(Connection connection) {
        super(connection);
        id = 0x03;
    }

    @Override
    public void read(PacketByteBuf buf) {
        message = buf.readString();
    }
}
