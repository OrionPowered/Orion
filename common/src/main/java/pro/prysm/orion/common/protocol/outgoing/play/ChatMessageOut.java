package pro.prysm.orion.common.protocol.outgoing.play;

import net.kyori.adventure.text.Component;
import pro.prysm.orion.server.message.ChatPosition;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.outgoing.OutgoingPacket;

import java.util.UUID;

public class ChatMessageOut extends OutgoingPacket {
    private final ChatPosition position;
    private final UUID sender;
    private final Component message;

    public ChatMessageOut(ChatPosition position, UUID sender, Component message) {
        super(0x0F);
        this.position = position;
        this.sender = sender;
        this.message = message;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeComponent(message);
        buf.writeByte(position.getValue());
        buf.writeUuidIntArray(sender);

    }
}
