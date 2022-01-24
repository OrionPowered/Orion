package pro.prysm.orion.server.protocol.outgoing.play;

import lombok.Getter;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;
import pro.prysm.orion.server.world.Chunk;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class UpdateLight extends OutgoingPacket {
    @Getter
    private final int x, z;
    List<byte[]> skyLight;
    List<byte[]> blockLight;

    protected UpdateLight(Chunk chunk) {
        super(0x25);
        x = chunk.getX();
        z = chunk.getZ();
        skyLight = chunk.getSkyLight();
        blockLight = chunk.getBlockLight();
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

        BitSet skyLightMask = new BitSet();
        BitSet blockLightMask = new BitSet();
        BitSet emptySkylightMask = new BitSet();
        BitSet emptyBlockLightMask = new BitSet();
        // Sky lighting
        skyLight = calculateLighting(skyLightMask, emptySkylightMask, skyLight);

        // Block lighting
        blockLight = calculateLighting(blockLightMask, emptyBlockLightMask, blockLight);

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

    private List<byte[]> calculateLighting(BitSet mask, BitSet emptyMask, List<byte[]> lightData) {
        ArrayList<byte[]> data = new ArrayList<>();
        for (int i = 0; i < lightData.size(); i++) {
            byte[] array = lightData.get(i);
            if (array.length == 0) {
                emptyMask.set(i, true);
            } else {
                mask.set(i, true);
                data.add(array);
            }
        }
        return data;
    }
}
