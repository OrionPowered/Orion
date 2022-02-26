package pro.prysm.orion.common.protocol.outgoing.login;

import lombok.Getter;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.outgoing.OutgoingPacket;

import java.util.UUID;

@Getter
public class LoginSuccess extends OutgoingPacket {
    private final String username;
    private final UUID uniqueId;

    public LoginSuccess(UUID uniqueId, String username) {
        super(0x02);
        this.uniqueId = uniqueId;
        this.username = username;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeUuidIntArray(uniqueId);
        buf.writeString(username);
    }
}
