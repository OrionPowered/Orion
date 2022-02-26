package pro.prysm.orion.server.protocol.incoming;

import lombok.Getter;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.incoming.IncomingPacket;

@Getter
public class TeleportConfirm extends IncomingPacket {
    private int teleportId;

    @Override
    public void read(PacketByteBuf buf) {
        teleportId = buf.readVarInt();
    }
}
