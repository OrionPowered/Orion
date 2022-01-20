package pro.prysm.orion.server.protocol.outgoing.play;

import net.kyori.adventure.text.Component;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

public class PlayerlistHeaderFooter extends OutgoingPacket {
    private final Component header;
    private final Component footer;


    public PlayerlistHeaderFooter(Component header, Component footer) {
        super(0x5F);
        this.header = header;
        this.footer = footer;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeComponent(header);
        buf.writeComponent(footer);
    }
}
