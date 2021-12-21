package pro.prysm.orion.server.protocol.outgoing.login;

import io.netty.buffer.ByteBuf;
import pro.prysm.orion.server.protocol.Protocol;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

import java.util.UUID;

public class LoginSuccess extends OutgoingPacket implements pro.prysm.orion.api.protocol.outgoing.login.LoginSuccess {
    private final String username;
    private final UUID uniqueId;
    public LoginSuccess(Protocol protocol, UUID uniqueId, String username) {
        super(protocol);
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
    public void write(ByteBuf buf) {
        writeString(uniqueId.toString(), buf);
        writeString(username, buf);
    }
}
