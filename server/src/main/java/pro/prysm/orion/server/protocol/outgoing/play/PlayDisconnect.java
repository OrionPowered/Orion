package pro.prysm.orion.server.protocol.outgoing.play;

import net.kyori.adventure.text.Component;
import pro.prysm.orion.server.protocol.outgoing.login.LoginDisconnected;

public class PlayDisconnect extends LoginDisconnected {
    public PlayDisconnect(Component message) {
        super(0x1A, message);
    }
}
