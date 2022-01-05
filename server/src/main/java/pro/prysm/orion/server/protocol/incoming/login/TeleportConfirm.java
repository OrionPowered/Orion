package pro.prysm.orion.server.protocol.incoming.login;

import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

public class TeleportConfirm extends IncomingPacket {
    private int teleportId;

    public TeleportConfirm(Connection connection) {
        super(connection);
    }

    public int getTeleportId() {
        return teleportId;
    }

    @Override
    public void read(PacketByteBuf buf) {
        teleportId = buf.readVarInt();
        connection.getHandler().handle(this);
    }
}
