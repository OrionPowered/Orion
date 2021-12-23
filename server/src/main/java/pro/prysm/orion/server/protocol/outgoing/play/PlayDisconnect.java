package pro.prysm.orion.server.protocol.outgoing.play;

import pro.prysm.orion.server.protocol.outgoing.login.LoginDisconnected;

public class PlayDisconnect extends LoginDisconnected {

    public PlayDisconnect(String message) {
        super(message);
        id = 0x1A;
    }
}