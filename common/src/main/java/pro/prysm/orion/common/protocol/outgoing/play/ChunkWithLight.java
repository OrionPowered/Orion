package pro.prysm.orion.common.protocol.outgoing.play;

import io.netty.buffer.Unpooled;
import lombok.Getter;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import pro.prysm.orion.server.data.palette.PalettedContainer;
import pro.prysm.orion.server.data.palette.PalettedContainer.Type;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.outgoing.OutgoingPacket;
import pro.prysm.orion.server.world.Chunk;
import pro.prysm.orion.server.world.ChunkSection;

public class ChunkWithLight extends OutgoingPacket {
    private final Chunk chunk;
    private final boolean exists;
    private final CompoundBinaryTag heightmaps;
    @Getter
    private final int x, z;

    public ChunkWithLight(Chunk chunk) {
        super(0x22);
        this.chunk = chunk;
        this.exists = chunk.exists();
        x = chunk.getX();
        z = chunk.getZ();
        this.heightmaps = chunk.getHeightMaps().remove("MOTION_BLOCKING_NO_LEAVES").remove("OCEAN_FLOOR");
    }

    public boolean exists() {
        return exists;
    }

    private int[] generateBlockPaletteData(ChunkSection section) {
        int size = section.getBlockStatePalette().size();
        int[] data = new int[size];
        for (int i = 0; i < size; i++) data[i] = blockState(section, i);
        return data;
    }

    private int[] generateBiomePaletteData(ChunkSection section) {
        int size = section.getBiomePalette().size();
        int[] data = new int[size];
        for (int i = 0; i < size; i++) data[i] = biomeId(section, i);
        return data;
    }

    private int blockState(ChunkSection section, int index) {
        return section.getBlockState(index);
    }

    private int biomeId(ChunkSection section, int index) {
        return section.getBiomeEntry(index).getId();
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(z);
        buf.writeNBT(heightmaps);

        PacketByteBuf paletteBuf = new PacketByteBuf(Unpooled.buffer());
        chunk.getSections().forEach(section -> {
            paletteBuf.writeShort(section.getBlockCount());
            PalettedContainer blockContainer = PalettedContainer.from(Type.BLOCK_STATES, section.getBitsPerBlock(), generateBlockPaletteData(section), section.getBlockStates());
            PalettedContainer biomeContainer = PalettedContainer.from(Type.BIOME, section.getBitsPerBiome(), generateBiomePaletteData(section), section.getBiomes());
            blockContainer.write(paletteBuf);
            biomeContainer.write(paletteBuf);
        });

        buf.writeVarInt(paletteBuf.readableBytes());
        buf.writeBytes(paletteBuf);
        paletteBuf.release();

        buf.writeVarInt(0); // No block entities

        new UpdateLight(chunk).writePartial(buf);
    }
}
