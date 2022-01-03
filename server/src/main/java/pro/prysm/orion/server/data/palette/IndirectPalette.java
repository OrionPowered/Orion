package pro.prysm.orion.server.data.palette;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.server.protocol.PacketWriter;

public class IndirectPalette extends Palette {
    public IndirectPalette(byte bitsPerEntry) {
        this.bitsPerEntry = bitsPerEntry;
    }

    @Override
    public void write(ByteBuf buf) {
        PacketWriter.writeVarInt(bitsPerEntry, buf);

    }
}
