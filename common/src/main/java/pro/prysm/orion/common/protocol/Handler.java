package pro.prysm.orion.common.protocol;

import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.common.protocol.incoming.IncomingPacket;

public interface Handler {
    Handler getForState(PacketState state);
    void onDisconnect();
    void handle(IncomingPacket packet);
}
