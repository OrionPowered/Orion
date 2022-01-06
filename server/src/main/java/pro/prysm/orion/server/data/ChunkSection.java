package pro.prysm.orion.server.data;

import net.kyori.adventure.nbt.BinaryTag;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import pro.prysm.orion.server.net.PacketByteBuf;

public class ChunkSection extends com.alexsobiek.anvil.ChunkSection {

    public ChunkSection(CompoundBinaryTag nbt) {
        super(nbt);
    }

    public void write(PacketByteBuf buf) {
        buf.writeShort(getBlockCount());
        buf.writeByte(getBitsPerBlock());

        // write palette
        buf.writeVarInt(getPalette().size());
        for (BinaryTag tag : getPalette()) {
            buf.writeVarInt(1);
        }

        buf.writeLongArray(getStates());
    }
}