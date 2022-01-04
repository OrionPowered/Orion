package pro.prysm.orion.server.protocol.outgoing.play;

import com.alexsobiek.anvil.Chunk;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import pro.prysm.orion.server.data.ChunkSection;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

public class OutgoingChunk extends OutgoingPacket {
    private final int x, z;
    private final CompoundBinaryTag heightmaps;
    private ChunkSection[] sections;

    private int bitsPerBlock = 5;
    private short blockCount = Chunk.SECTION_HEIGHT * Chunk.SECTION_WIDTH * Chunk.SECTION_HEIGHT;

    public OutgoingChunk(com.alexsobiek.anvil.Chunk chunk) {
        id = 0x22;
        this.x = chunk.getX();
        this.z = chunk.getZ();
        this.heightmaps = chunk.getHeightmaps().remove("MOTION_BLOCKING_NO_LEAVES").remove("OCEAN_FLOOR").remove("WORLD_SURFACE");
        System.out.println(heightmaps);
        this.sections = new ChunkSection[16];
        for (int i = 0; i < 16; i++) sections[i] = new ChunkSection(chunk.getSections().getCompound(i));
    }

    @Override
    public void write(ByteBuf buf) {
        int mask = 0;
        ByteBuf columnBuf = Unpooled.buffer();
        buf.writeInt(x);
        buf.writeInt(z);

        for (int y = 0; y < (Chunk.HEIGHT/Chunk.SECTION_WIDTH); y++) {
            mask |= (1 << y);
            sections[y].write(columnBuf);
        }

        // Write temporary ocean biome data
        for (int i = 0; i < (Chunk.SECTION_HEIGHT*Chunk.SECTION_WIDTH); i++) {
            columnBuf.writeInt(127);
        }

        writeVarInt(mask, buf);
        writeNBT(heightmaps, buf);
        writeVarInt(columnBuf.readableBytes(), buf);
        buf.writeBytes(columnBuf);
        columnBuf.release();
    }
}
