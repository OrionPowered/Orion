package pro.prysm.orion.common.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author 254n_m
 * @since 12/18/21 / 8:37 PM
 * This file was created as a part of Orion
 */
@Getter
@RequiredArgsConstructor
public abstract class Command {
    private final String name;
    private final String description;

    public abstract void execute(String[] args, String name);
}
