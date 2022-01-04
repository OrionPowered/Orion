package pro.prysm.orion.server.protocol.outgoing.play;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

import java.util.Random;

public class PlayerPositionAndLook extends OutgoingPacket {
    private final Location loc;
    private final int teleportId;

    public PlayerPositionAndLook(Location location) {
        id = 0x38;
        this.loc = location;
        this.teleportId = new Random().nextInt(9999);
    }

    @Override
    public void write(ByteBuf buf) {
        writeLocation(loc, buf);
        buf.writeByte(0x0); // TODO: Implement bit field
        writeVarInt(teleportId, buf);
        buf.writeBoolean(true);
    }
}
