package pro.prysm.orion.server.protocol.incoming.login;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.server.net.Connection;
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
    public void read(ByteBuf buf) {
        teleportId = readVarInt(buf);
        connection.getHandler().handle(this);
    }
}
