package pro.prysm.orion.server.protocol.incoming;

import lombok.Getter;
import pro.prysm.orion.common.net.Connection;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.incoming.IncomingPacket;

@Getter
public class TeleportConfirm extends IncomingPacket {
    private int teleportId;

    public TeleportConfirm(Connection connection) {
        super(connection);
    }

    @Override
    public void read(PacketByteBuf buf) {
        teleportId = buf.readVarInt();
        connection.getHandler().handle(this);
    }
}
