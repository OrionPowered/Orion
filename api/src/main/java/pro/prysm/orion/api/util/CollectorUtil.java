package pro.prysm.orion.api.util;

import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollectorUtil {
    public static <T> Collector<T, ?, T> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() == 0) throw new NullPointerException();
                    else if (list.size() > 1) throw new IllegalStateException();
                    else return list.get(0);
                }
        );
    }
}
