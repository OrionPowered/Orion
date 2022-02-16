package pro.prysm.orion.common.protocol.outgoing.play;

import lombok.Getter;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.outgoing.OutgoingPacket;
import pro.prysm.orion.server.world.Chunk;
import pro.prysm.orion.server.world.ChunkSection;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class UpdateLight extends OutgoingPacket {
    @Getter
    private final int x, z;
    private final BitSet skyLightMask;
    private final BitSet blockLightMask;
    private final BitSet emptySkylightMask;
    private final BitSet emptyBlockLightMask;
    private final List<byte[]> skyLight;
    private final List<byte[]> blockLight;

    protected UpdateLight(Chunk chunk) {
        super(0x25);
        x = chunk.getX();
        z = chunk.getZ();

        skyLightMask = chunk.getSkyLightMask();
        blockLightMask = chunk.getBlockLightMask();
        emptySkylightMask = chunk.getSkyLightEmptyMask();
        emptyBlockLightMask = chunk.getBlockLightEmptyMask();

        skyLight = new ArrayList<>();
        blockLight = new ArrayList<>();
        List<ChunkSection> sections = chunk.getSections();
        sections.forEach(section -> {
            if (section.hasSkyLight()) skyLight.add(section.getSkyLight());
            if (section.hasBlockLight()) blockLight.add(section.getBlockLight());
        });
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeVarInt(x);
        buf.writeVarInt(z);
        writePartial(buf);
    }

    // Used both in this packet and Chunk Data
    public void writePartial(PacketByteBuf buf) {
        buf.writeBoolean(true);   // trust edges? <-- TODO

        buf.writeBitSet(skyLightMask);
        buf.writeBitSet(blockLightMask);

        buf.writeBitSet(emptySkylightMask);
        buf.writeBitSet(emptyBlockLightMask);

        buf.writeVarInt(skyLight.size());
        for (byte[] array : skyLight) {
            buf.writeByteArray(array);
        }

        buf.writeVarInt(blockLight.size());
        for (byte[] array : blockLight) {
            buf.writeByteArray(array);
        }
    }
}
