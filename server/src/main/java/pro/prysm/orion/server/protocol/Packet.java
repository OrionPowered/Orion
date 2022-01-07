package pro.prysm.orion.server.protocol;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pro.prysm.orion.api.protocol.PacketDirection;
import pro.prysm.orion.api.protocol.PacketState;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Packet implements pro.prysm.orion.api.protocol.Packet {
    protected final int id;
    protected PacketDirection direction;
    protected PacketState state;
}
