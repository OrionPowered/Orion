package pro.prysm.orion.server.util;

import net.kyori.adventure.nbt.*;

import java.util.ArrayList;
import java.util.List;

public class TagUtil {
    public static ListBinaryTag byteList(byte... bytes) {
        List<BinaryTag> binaryTags = new ArrayList<>();
        for(byte b : bytes) binaryTags.add(ByteBinaryTag.of(b));
        return ListBinaryTag.of(BinaryTagTypes.BYTE, binaryTags);
    }

    public static ListBinaryTag shortList(short... shorts) {
        List<BinaryTag> binaryTags = new ArrayList<>();
        for(short s : shorts) binaryTags.add(ShortBinaryTag.of(s));
        return ListBinaryTag.of(BinaryTagTypes.SHORT, binaryTags);
    }

    public static ListBinaryTag intList(int... ints) {
        List<BinaryTag> binaryTags = new ArrayList<>();
        for(int i : ints) binaryTags.add(IntBinaryTag.of(i));
        return ListBinaryTag.of(BinaryTagTypes.INT, binaryTags);
    }

    public static ListBinaryTag longList(long... longs) {
        List<BinaryTag> binaryTags = new ArrayList<>();
        for(long l : longs) binaryTags.add(LongBinaryTag.of(l));
        return ListBinaryTag.of(BinaryTagTypes.LONG, binaryTags);
    }

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
