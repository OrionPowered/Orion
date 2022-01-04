package pro.prysm.orion.server.protocol.incoming.play;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

public class ChatMessage extends IncomingPacket implements pro.prysm.orion.api.protocol.incoming.play.ChatMessage {
    private String message;

    public ChatMessage(Connection connection) {
        super(connection);
        id = 0x03;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void read(ByteBuf buf) {
        message = readString(buf);
    }
}
