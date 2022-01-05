package pro.prysm.orion.server.protocol.outgoing.login;

import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

import java.util.UUID;

public class LoginSuccess extends OutgoingPacket implements pro.prysm.orion.api.protocol.outgoing.login.LoginSuccess {
    private final String username;
    private final UUID uniqueId;

    public LoginSuccess(UUID uniqueId, String username) {
        id = 0x02;
        this.uniqueId = uniqueId;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeUuidIntArray(uniqueId);
        buf.writeString(username);
    }
}
