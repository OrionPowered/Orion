package pro.prysm.orion.server.data.palette;

import com.alexsobiek.anvil.ChunkSection;
import net.kyori.adventure.nbt.ListBinaryTag;
import pro.prysm.orion.api.data.Block;
import pro.prysm.orion.server.net.PacketByteBuf;

public class BlockStatePalette {
    private final ChunkSection section;
    private int bitsPerBlock;

    public BlockStatePalette(ChunkSection section) {
        this.section = section;
        bitsPerBlock = section.getBitsPerBlock();
    }

    private int blockState(int index) {
        // section.getPaletteEntry(i).getCompound("Properties");  TODO: use proper states
        Block block = Block.getBlock(section.getBlockStatePaletteEntry(index).getString("Name").replace("minecraft:", ""));
        return block.getDefaultState();
    }

    public void write(PacketByteBuf buf) {
        buf.writeShort(section.getBlockCount());
        if (bitsPerBlock <= 4 && bitsPerBlock != 0) bitsPerBlock = 4;
        buf.writeVarInt(bitsPerBlock);


        ListBinaryTag blockStatePalette = section.getBlockStatePalette();

        if (bitsPerBlock == 0) { // Single value palette
            buf.writeVarInt(blockState(0));
        } else { // Indirect palette
            buf.writeVarInt(blockStatePalette.size());
            for (int i = 0; i < blockStatePalette.size(); i++) buf.writeVarInt(blockState(i)); // write block states
        } // else - direct palette (no values)

        // Write block states
        buf.writeLongArray(section.getStates());
    }

}
