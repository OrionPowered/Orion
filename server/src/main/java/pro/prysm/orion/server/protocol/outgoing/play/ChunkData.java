package pro.prysm.orion.server.protocol.outgoing.play;

import com.alexsobiek.anvil.Chunk;
import com.alexsobiek.anvil.ChunkSection;
import io.netty.buffer.Unpooled;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

import java.util.List;

public class ChunkData extends OutgoingPacket {
    private final Chunk chunk;
    private int x, z;
    private final CompoundBinaryTag heightmaps;
    List<byte[]> skyLight;
    List<byte[]> blockLight;

    public ChunkData(Chunk chunk) {
        id = 0x22;
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
        chunk.getSections().forEach(section -> writeSection(section, columnBuf));

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
        for (byte[] array : skyLight) {
            buf.writeVarInt(array.length);
            buf.writeBytes(array);
        }

        buf.writeVarInt(blockLight.size());
        for (byte[] array : blockLight) {
            buf.writeVarInt(array.length);
            buf.writeBytes(array);
        }
    }

    public void writeSection(ChunkSection section, PacketByteBuf buf) {
        buf.writeShort(section.getBlockCount());

        // BLOCK STATES CONTAINER
        buf.writeByte(section.getBitsPerBlock());

        // write block states palette
        buf.writeVarInt(section.getPalette().size());
        for (int i = 0; i < section.getPalette().size(); i++) {
            buf.writeVarInt(1); // Stone
        }

        // Write block states

        buf.writeLongArray(section.getStates());
        // END BLOCK STATES CONTAINER


        // BIOME CONTAINER
        // Biome Palette
        buf.writeByte(6);
        buf.writeLongArray(new long[64]);
        // END BIOME CONTAINER
    }

}
