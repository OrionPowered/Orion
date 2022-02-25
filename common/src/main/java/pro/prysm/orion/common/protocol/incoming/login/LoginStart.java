package pro.prysm.orion.common.protocol.incoming.login;

import lombok.Getter;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.incoming.IncomingPacket;

@Getter
public class LoginStart extends IncomingPacket {
    private String username;

    @Override
    public void read(PacketByteBuf buf) {
        username = buf.readString();
    }
}
