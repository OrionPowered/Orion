package pro.prysm.orion.common.protocol.outgoing.login;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.outgoing.OutgoingPacket;

@Getter
public class LoginDisconnected extends OutgoingPacket {
    private final Component reason;

    public LoginDisconnected(Component reason) {
        super(0x00);
        this.reason = reason;
    }

    public LoginDisconnected(int id, Component reason) {
        super(id);
        this.reason = reason;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeComponent(reason);
    }
}
