package pro.prysm.orion.server.protocol.outgoing.play;

import net.kyori.adventure.text.Component;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

public class ActionBar extends OutgoingPacket {
    private final Component message;

    public ActionBar(Component message) {
        super(0x41);
        this.message = message;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeComponent(message);
    }
}
