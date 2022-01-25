package pro.prysm.orion.api.extension.plugin;

import lombok.Getter;
import pro.prysm.orion.api.Server;
import pro.prysm.orion.api.extension.AbstractExtension;

/**
 * @author 254n_m
 * @since 12/20/21 / 9:25 PM
 * This file was created as a part of Orion
 */
@Getter
public abstract class JavaPlugin extends AbstractExtension {
    protected Server server;
}
