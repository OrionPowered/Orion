package pro.prysm.orion.server.protocol;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pro.prysm.orion.api.protocol.PacketDirection;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Packet {
    protected PacketDirection direction;
}
