package pro.prysm.orion.server.protocol.incoming.play;

import lombok.Getter;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

@Getter
public class ChatMessage extends IncomingPacket {
    private String message;

    public ChatMessage(Connection connection) {
        super(connection);
    }

    @Override
    public void read(PacketByteBuf buf) {
        message = buf.readString();
    }
}
