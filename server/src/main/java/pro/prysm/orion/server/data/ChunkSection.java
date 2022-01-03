package pro.prysm.orion.server.data;

import io.netty.buffer.ByteBuf;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import pro.prysm.orion.server.data.palette.Palette;
import pro.prysm.orion.server.protocol.PacketWriter;

public class ChunkSection extends com.alexsobiek.anvil.ChunkSection {

    public ChunkSection(CompoundBinaryTag nbt) {
        super(nbt);
    }

    public void write(ByteBuf buf) {
        if (getData().length > 0) {
            buf.writeShort(getBlockCount());
            buf.writeByte(getBitsPerBlock());
            Palette.from(getBitsPerBlock()).write(buf);
            PacketWriter.writeLongArray(getData(), buf);
        }
    }
}