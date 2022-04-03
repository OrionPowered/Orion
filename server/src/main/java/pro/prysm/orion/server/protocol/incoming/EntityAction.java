package pro.prysm.orion.server.protocol.incoming;

import lombok.Getter;
import lombok.ToString;
import pro.prysm.orion.api.util.CollectorUtil;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.incoming.IncomingPacket;

import java.util.Arrays;

@Getter
@ToString
public class EntityAction extends IncomingPacket {
    private int entityId;
    private pro.prysm.orion.api.entity.player.EntityAction action;
    private int jumpBoost;

    @Override
    public void read(PacketByteBuf buf) {
        entityId = buf.readVarInt();
        int actionId = buf.readVarInt();
        action = Arrays.stream(pro.prysm.orion.api.entity.player.EntityAction.values()).filter(a -> a.getId() == actionId).collect(CollectorUtil.toSingleton());
        jumpBoost = buf.readVarInt();
    }
}
