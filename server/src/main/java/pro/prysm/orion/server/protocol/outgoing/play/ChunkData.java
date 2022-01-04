package pro.prysm.orion.server.protocol.outgoing.play;

import com.alexsobiek.anvil.Chunk;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import pro.prysm.orion.server.data.ChunkSection;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

public class ChunkData extends OutgoingPacket {
    private int x, z;
    private final CompoundBinaryTag heightmaps;
    private final ChunkSection[] sections;

    public ChunkData(Chunk chunk) {
        id = 0x22;
        this.x = chunk.getX();
        this.z = chunk.getZ();
        this.heightmaps = chunk.getHeightmaps().remove("MOTION_BLOCKING_NO_LEAVES").remove("OCEAN_FLOOR");
        this.sections = new ChunkSection[Chunk.SECTION_TOTAL];
        for (int i = 0; i < Chunk.SECTION_TOTAL; i++) sections[i] = new ChunkSection(chunk.getSections().getCompound(i));
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(z);
        writeNBT(heightmaps, buf);

        ByteBuf columnBuf = Unpooled.buffer();
        int mask = 0;
        for (int y = 0; y < Chunk.SECTION_TOTAL; y++) {
            if (sections[y].getStates().length > 0) {
                mask |= (1 << y);
                sections[y].write(columnBuf);
            }
        }

        // Write temporary ocean biome data
        for (int i = 0; i < (Chunk.SECTION_HEIGHT*Chunk.SECTION_WIDTH); i++) {
            columnBuf.writeInt(0);
        }

        //writeVarInt(mask, buf);
        writeVarInt(columnBuf.readableBytes(), buf);

        buf.writeBytes(columnBuf);
        columnBuf.release();
    }
}