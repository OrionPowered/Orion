package pro.prysm.orion.server.protocol.outgoing.play;

import pro.prysm.orion.api.chat.Message;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

public class PlayerlistHeaderFooter extends OutgoingPacket {
    private final Message header;
    private final Message footer;


    public PlayerlistHeaderFooter(Message header, Message footer) {
        super(0x5F);
        this.header = header;
        this.footer = footer;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeString(header.toJsonString());
        buf.writeString(footer.toJsonString());
    }
}
