package pro.prysm.orion.api.message;

import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;

public interface ChatFormatter {
    Component format(Identity source, Component message);
}
