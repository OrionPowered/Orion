package pro.prysm.orion.server.protocol.incoming.play;

import lombok.Getter;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

@Getter
public class TeleportConfirm extends IncomingPacket {
    private int teleportId;

    public TeleportConfirm(Connection connection) {
        super(0x00, connection);
    }

    @Override
    public void read(PacketByteBuf buf) {
        teleportId = buf.readVarInt();
        connection.getHandler().handle(this);
    }
}
