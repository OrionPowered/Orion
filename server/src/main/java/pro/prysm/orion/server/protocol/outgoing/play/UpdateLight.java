package pro.prysm.orion.server.protocol.outgoing.play;

import com.alexsobiek.anvil.Chunk;
import io.netty.buffer.Unpooled;
import lombok.Getter;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

import java.util.Arrays;
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

        PacketByteBuf lightingBuf = new PacketByteBuf(Unpooled.buffer());

        // Sky lighting
        calculateAndWriteLighting(lightingBuf, skyLightMask, skyLight);

        // Block lighting
        calculateAndWriteLighting(lightingBuf, blockLightMask, blockLight);

        buf.writeBitSet(skyLightMask);
        buf.writeBitSet(blockLightMask);

        buf.writeBitSet(flipBitSet(skyLightMask));
        buf.writeBitSet(flipBitSet(blockLightMask));

        buf.writeBytes(lightingBuf);
        lightingBuf.release();
    }

    private void calculateAndWriteLighting(PacketByteBuf lightingBuf, BitSet mask, List<byte[]> lightData) {
        lightingBuf.writeVarInt(lightData.size());
        for (int i = 0; i < lightData.size(); i++) {
            byte[] array = lightData.get(i);
            if (array.length == 0) {
                array = new byte[2048]; // Sometimes we don't have any data, but the client needs it
                Arrays.fill(array, (byte) 0x0F); // Light the entire area
                mask.set(i);
            } else {
                for (byte b : array)
                    if (b > 0x00) {
                        mask.set(i);
                        break;
                    }
            }
            lightingBuf.writeByteArray(array);
        }
    }

    private BitSet flipBitSet(BitSet set) {
        BitSet flipped = (BitSet) set.clone();
        flipped.flip(0, set.length());
        return flipped;
    }
}
