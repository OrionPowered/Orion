package pro.prysm.orion.server.protocol.incoming.play;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

@Getter
public class ChatMessageIn extends IncomingPacket {
    private Component message;

    public ChatMessageIn(Connection connection) {
        super(connection);
    }

    @Override
    public void read(PacketByteBuf buf) {
        message = buf.readComponent();
        connection.getHandler().handle(this);
    }
}
