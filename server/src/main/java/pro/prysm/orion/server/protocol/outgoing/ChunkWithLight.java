package pro.prysm.orion.server.protocol.outgoing;

import io.netty.buffer.Unpooled;
import lombok.Getter;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.palette.PalettedContainer;
import pro.prysm.orion.common.protocol.outgoing.OutgoingPacket;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.world.Chunk;
import pro.prysm.orion.server.world.ChunkSection;
import pro.prysm.orion.server.world.ChunkStatus;

@Getter
public class ChunkWithLight extends OutgoingPacket {
    private final Chunk chunk;
    private final CompoundBinaryTag heightmaps;

    public ChunkWithLight(Chunk chunk) {
        super(0x22);
        this.chunk = chunk;
        this.heightmaps = chunk.getHeightMaps().remove("MOTION_BLOCKING_NO_LEAVES").remove("OCEAN_FLOOR");
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
        if (chunk.getStatus() != ChunkStatus.FULL) {
            Orion.getLogger().warn("Tried to send non-full chunk to player at {}, {}", chunk.getX(), chunk.getZ());
        } else {
            buf.writeInt(chunk.getX());
            buf.writeInt(chunk.getZ());
            buf.writeNBT(heightmaps);
            PacketByteBuf paletteBuf = new PacketByteBuf(Unpooled.buffer());
            chunk.getSections().forEach(section -> {
                paletteBuf.writeShort(section.getBlockCount());
                PalettedContainer blockContainer = PalettedContainer.from(PalettedContainer.Type.BLOCK_STATES, section.getBitsPerBlock(), generateBlockPaletteData(section), section.getBlockStates());
                PalettedContainer biomeContainer = PalettedContainer.from(PalettedContainer.Type.BIOME, section.getBitsPerBiome(), generateBiomePaletteData(section), section.getBiomes());
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
}
