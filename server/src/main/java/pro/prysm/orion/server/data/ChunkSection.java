package pro.prysm.orion.server.data;

import io.netty.buffer.ByteBuf;
import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import pro.prysm.orion.server.protocol.PacketWriter;

public class ChunkSection extends com.alexsobiek.anvil.ChunkSection {

    public ChunkSection(CompoundBinaryTag nbt) {
        super(nbt);
    }

    public void write(ByteBuf buf) {
        buf.writeShort(getBlockCount());
        buf.writeByte(getBitsPerBlock());

        // write palette
        PacketWriter.writeVarInt(getPalette().size(), buf);
        for (BinaryTag tag : getPalette()) {
            PacketWriter.writeVarInt(1, buf);
        }

        PacketWriter.writeLongArray(getStates(), buf);
    }
}