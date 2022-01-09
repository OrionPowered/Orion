package pro.prysm.orion.server.util;

public class MathUtil {
    public static float clamp(double val, double min, double max) {
        return (float) Math.max(min, Math.min(max, val));
    }
}
