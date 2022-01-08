package pro.prysm.orion.server.util;

import net.kyori.adventure.nbt.*;

import java.util.ArrayList;
import java.util.List;

public class TagUtil {
    public static ListBinaryTag floatList(float... floats) {
        List<BinaryTag> binaryTags = new ArrayList<>();
        for(float f : floats) binaryTags.add(FloatBinaryTag.of(f));
        return ListBinaryTag.of(BinaryTagTypes.FLOAT, binaryTags);
    }

    public static ListBinaryTag doubleList(double... doubles) {
        List<BinaryTag> binaryTags = new ArrayList<>();
        for(double d : doubles) binaryTags.add(DoubleBinaryTag.of(d));
        return ListBinaryTag.of(BinaryTagTypes.DOUBLE, binaryTags);
    }
}
