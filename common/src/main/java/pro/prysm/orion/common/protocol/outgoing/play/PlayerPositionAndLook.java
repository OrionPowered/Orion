package pro.prysm.orion.common.protocol.outgoing.play;

import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.outgoing.OutgoingPacket;

import java.util.Random;

public class PlayerPositionAndLook extends OutgoingPacket {
    private final Location loc;
    private final int teleportId;

    public PlayerPositionAndLook(Location location) {
        super(0x38);
        this.loc = location;
        this.teleportId = new Random().nextInt(9999);
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeLocation(loc);
        buf.writeByte(0x0); // TODO: Implement bit field
        buf.writeVarInt(teleportId);
        buf.writeBoolean(true);
    }
}
