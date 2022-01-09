package pro.prysm.orion.server.protocol.outgoing.play;

import com.alexsobiek.anvil.Chunk;
import com.alexsobiek.anvil.ChunkSection;
import io.netty.buffer.Unpooled;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import pro.prysm.orion.api.data.Biome;
import pro.prysm.orion.api.data.Block;
import pro.prysm.orion.server.data.palette.PalettedContainer;
import pro.prysm.orion.server.data.palette.PalettedContainer.Type;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

import java.util.ArrayList;
import java.util.List;

public class ChunkData extends OutgoingPacket {
    private final Chunk chunk;
    private final CompoundBinaryTag heightmaps;
    List<byte[]> skyLight;
    List<byte[]> blockLight;
    private final int x, z;

    public ChunkData(Chunk chunk) {
        super(0x22);
        this.chunk = chunk;
        x = chunk.getX();
        z = chunk.getZ();
        this.heightmaps = chunk.getHeightmaps().remove("MOTION_BLOCKING_NO_LEAVES").remove("OCEAN_FLOOR");
        skyLight = chunk.getSkyLight();
        blockLight = chunk.getBlockLight();
    }

    private int[] generateBlockPaletteData(ChunkSection section) {
        ArrayList<Integer> data = new ArrayList<>();
        int size = section.getBlockStatePalette().size();
        for (int i = 0; i < size; i++) data.add(blockState(section, i));
        return data.stream().mapToInt(Integer::intValue).toArray();
    }

    private int[] generateBiomePaletteData(ChunkSection section) {
        ArrayList<Integer> data = new ArrayList<>();
        int size = section.getBiomePalette().size();
        for (int i = 0; i < size; i++) data.add(biomeId(section, i));
        return data.stream().mapToInt(Integer::intValue).toArray();
    }

    private int blockState(ChunkSection section, int index) {
        // section.getPaletteEntry(i).getCompound("Properties");  TODO: use proper states
        Block block = Block.getBlock(section.getBlockStatePaletteEntry(index).getString("Name").replace("minecraft:", ""));
        return block.getDefaultState();
    }

    private int biomeId(ChunkSection section, int index) {
        return Biome.getBiome(section.getBiomePaletteEntry(index).replace("minecraft:", "")).getId();
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(z);
        buf.writeNBT(heightmaps);

        PacketByteBuf columnBuf = new PacketByteBuf(Unpooled.buffer());
        chunk.getSections().forEach(section -> {
            PalettedContainer blockContainer = PalettedContainer.from(Type.BLOCK_STATES, section.getBitsPerBlock(), generateBlockPaletteData(section), section.getStates());
            PalettedContainer biomeContainer = PalettedContainer.from(Type.BIOME, section.getBitsPerBiome(), generateBiomePaletteData(section), section.getBiomes());
            blockContainer.write(columnBuf);
            biomeContainer.write(columnBuf);
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
