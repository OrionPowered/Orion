package pro.prysm.orion.api.message;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public interface PlaceholderService {
    /**
     * Registers a static placeholder
     * @param key Placeholder Key
     * @param value Placeholder return value
     */
    void register(String key, String value);

    /**
     * Registers a dynamic placeholder
     * @param key Placeholder Key
     * @param value Placeholder to call when resolving (Must return String)
     */
    void register(String key, Placeholder<?> value);

    /**
     * Resolves all registered placeholders in the provided String
     * @param message String to resolve placeholders in
     * @return String with resolved placeholders
     */
    String resolve(String message);
}
