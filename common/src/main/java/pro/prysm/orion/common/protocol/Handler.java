package pro.prysm.orion.common.protocol;

import pro.prysm.orion.api.protocol.PacketState;

public interface Handler {
    Handler getForState(PacketState state);
    void onDisconnect();
}
