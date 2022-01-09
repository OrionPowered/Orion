package pro.prysm.orion.server.protocol.outgoing.play;

import pro.prysm.orion.api.data.Location;
import pro.prysm.orion.api.data.Velocity;
import pro.prysm.orion.server.entity.ImplLivingEntity;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;
import pro.prysm.orion.server.util.MathUtil;

import java.util.UUID;

public class SpawnLivingEntity extends OutgoingPacket {
    private int entityId;
    private UUID entityUUID;
    private int entityType;
    private Location location;
    private float headPitch;
    private int data;
    private Velocity velocity;

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
        buf.writeShort((int)(MathUtil.clamp(location.getX(), -3.9D, 3.9D) * 8000.0D)); // X Vel
        buf.writeShort((int)(MathUtil.clamp(location.getY(), -3.9D, 3.9D) * 8000.0D)); // Y Vel
        buf.writeShort((int)(MathUtil.clamp(location.getZ(), -3.9D, 3.9D) * 8000.0D)); // Z Vel
    }
}