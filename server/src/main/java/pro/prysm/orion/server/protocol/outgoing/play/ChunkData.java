package pro.prysm.orion.server.protocol.outgoing.play;

import com.alexsobiek.anvil.Chunk;
import io.netty.buffer.Unpooled;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import pro.prysm.orion.server.data.palette.BiomePalette;
import pro.prysm.orion.server.data.palette.BlockStatePalette;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

import java.util.List;

public class ChunkData extends OutgoingPacket {
    private final Chunk chunk;
    private final CompoundBinaryTag heightmaps;
    List<byte[]> skyLight;
    List<byte[]> blockLight;
    private int x, z;

    public ChunkData(Chunk chunk) {
        super(0x22);
        this.chunk = chunk;
        x = chunk.getX();
        z = chunk.getZ();
        this.heightmaps = chunk.getHeightmaps().remove("MOTION_BLOCKING_NO_LEAVES").remove("OCEAN_FLOOR");
        skyLight = chunk.getSkyLight();
        blockLight = chunk.getBlockLight();
    }

    // Setters are for debug purposes only!
    public void setX(int x) {
        this.x = x;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(z);
        buf.writeNBT(heightmaps);

        PacketByteBuf columnBuf = new PacketByteBuf(Unpooled.buffer());
        chunk.getSections().forEach(section -> {
            new BlockStatePalette(section).write(columnBuf);
            new BiomePalette(section).write(columnBuf);
        });

        buf.writeVarInt(columnBuf.readableBytes());
        buf.writeBytes(columnBuf);
        columnBuf.release();

        buf.writeByte(0); // No block entities

        buf.writeBoolean(true);   // trust edges? <-- TODO
        buf.writeBitSet(chunk.getSkyLightMask());
        buf.writeBitSet(chunk.getBlockLightMask());
        buf.writeBitSet(chunk.getEmptySkyLightMask());
        buf.writeBitSet(chunk.getEmptyBlockLightMask());

        buf.writeVarInt(skyLight.size());
        for (byte[] array : skyLight) buf.writeByteArray(array);

        buf.writeVarInt(blockLight.size());
        for (byte[] array : blockLight) buf.writeByteArray(array);
    }
}
