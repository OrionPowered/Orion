package pro.prysm.orion.server.protocol.outgoing;

import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.server.math.Velocity;
import pro.prysm.orion.server.entity.ImplLivingEntity;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.outgoing.OutgoingPacket;
import pro.prysm.orion.common.util.MathUtil;

import java.util.UUID;

public class SpawnLivingEntity extends OutgoingPacket {
    private final int entityId;
    private final UUID entityUUID;
    private final int entityType;
    private final Location location;
    private final float headPitch;
    private final Velocity velocity;
    private int data;

    public SpawnLivingEntity(ImplLivingEntity entity) {
        super(0x00);
        this.entityId = entity.getEntityId();
        this.entityUUID = entity.getUuid();
        this.entityType = entity.getEntityTypeId();
        this.location = entity.getLocation();
        this.headPitch = entity.getHeadPitch();
        this.velocity = entity.getVelocity();
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeVarInt(entityId);
        buf.writeUuidIntArray(entityUUID);
        buf.writeVarInt(entityType);
        buf.writeDouble(location.getX());
        buf.writeDouble(location.getY());
        buf.writeDouble(location.getZ());
        // TODO: Write angles properly
        buf.writeByte(0); // Pitch
        buf.writeByte(0); // Yaw
        buf.writeByte(0); // Head pitch
        buf.writeShort((int) (MathUtil.clamp(location.getX(), -3.9D, 3.9D) * 8000.0D)); // X Vel
        buf.writeShort((int) (MathUtil.clamp(location.getY(), -3.9D, 3.9D) * 8000.0D)); // Y Vel
        buf.writeShort((int) (MathUtil.clamp(location.getZ(), -3.9D, 3.9D) * 8000.0D)); // Z Vel
    }
}
