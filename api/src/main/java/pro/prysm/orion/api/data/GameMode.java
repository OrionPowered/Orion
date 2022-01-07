package pro.prysm.orion.api.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GameMode {
    SURVIVAL(0),
    CREATIVE(1),
    ADVENTURE(2),
    SPECTATOR(3),
    UNKNOWN(-1);

    private final int id;
}
