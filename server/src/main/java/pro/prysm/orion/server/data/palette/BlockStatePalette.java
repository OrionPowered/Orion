package pro.prysm.orion.server.data.palette;

import com.alexsobiek.anvil.ChunkSection;
import net.kyori.adventure.nbt.ListBinaryTag;
import pro.prysm.orion.api.data.Block;
import pro.prysm.orion.server.net.PacketByteBuf;

public class BlockStatePalette {
    private final ChunkSection section;

    public BlockStatePalette(ChunkSection section) {
        this.section = section;
    }

    public void write(PacketByteBuf buf) {
        buf.writeShort(section.getBlockCount());
        buf.writeByte(section.getBitsPerBlock());

        ListBinaryTag blockStatePalette = section.getBlockStatePalette();

        // write block states palette
        buf.writeVarInt(blockStatePalette.size());

        for (int i = 0; i < blockStatePalette.size(); i++) {
            // section.getPaletteEntry(i).getCompound("Properties");  TODO: use proper states
            Block block = Block.getBlock(section.getBlockStatePaletteEntry(i).getString("Name").replace("minecraft:", ""));
            buf.writeVarLong(block.getDefaultState());
        }

        // Write block states

        buf.writeLongArray(section.getStates());
    }

}
