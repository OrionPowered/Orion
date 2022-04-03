package pro.prysm.orion.api.entity.player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EntityAction {
    START_SNEAKING(0),
    STOP_SNEAKING(1),
    LEAVE_BED(2),
    START_SPRINTING(3),
    STOP_SPRINTING(4),
    START_JUMP_WITH_HORSE(5),
    STOP_JUMP_WITH_HORSE(6),
    OPEN_HORSE_INVENTORY(7),
    START_FLYING_WITH_ELYTRA(8);

    private final int id;
}
