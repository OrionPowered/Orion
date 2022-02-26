package pro.prysm.orion.common.message;

import pro.prysm.orion.api.message.Placeholder;
import pro.prysm.orion.common.OrionExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceholderService implements pro.prysm.orion.api.message.PlaceholderService {
    // Placeholders map should have either a String or a Method that can be invoked
    private final Map<String, Object> placeholders;
    private final Pattern pattern;

    public PlaceholderService() {
        placeholders = new HashMap<>();
        pattern = Pattern.compile("%(.*?)%");
    }

    public void register(String key, String value) {
        placeholders.put(sanitizeKey(key), value);
    }

    public void register(String key, Placeholder<?> value) {
        placeholders.put(sanitizeKey(key), value);
    }

    public String resolve(String message) {
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            if (placeholders.containsKey(sanitizeKey(matcher.group()))) {
                Object replacement = placeholders.get(sanitizeKey(matcher.group()));
                if (replacement instanceof String) {
                    message = message.replace(matcher.group(), (String) replacement);
                } else if (replacement instanceof Callable) {
                    try {
                        String res = ((Callable<?>) replacement).call().toString();
                        message = message.replace(matcher.group(), res);
                    } catch (Exception e) {
                        OrionExceptionHandler.error("Failed to invoke placeholder method", e);
                    }
                }
            }
        }
        return message;
    }

    private String sanitizeKey(String key) {
        if (key.startsWith("%")) key = key.substring(1);
        if (key.endsWith("%")) key = key.substring(0, key.length() - 1);
        return key.toLowerCase();
    }
}
