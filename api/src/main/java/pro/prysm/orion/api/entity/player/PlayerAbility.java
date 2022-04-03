package pro.prysm.orion.api.entity.player;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlayerAbility {
    NONE((byte) 0x00),
    FLYING((byte) 0x02);

    private final byte serverBoundId;
}
