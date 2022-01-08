package pro.prysm.orion.server.util;

import java.nio.charset.StandardCharsets;

public class ByteUtil {
    public static int readVarInt(byte[] buf) {
        int length = 0;
        int result = 0;
        byte read;
        do {
            read = buf[length];
            result |= (read & 0x7F) << (7 * length);
            length++;
            if (length > 5) {
                throw new RuntimeException("VarInt is too big");
            }
        } while ((read & 0x80) != 0);
        return result;
    }

    public static String readString(byte[] buf) {
        int size = readVarInt(buf);

        byte[] stringBytes = new byte[size];
        for(int i = 1; i < size; i++) stringBytes[i - 1] = buf[i];

        return new String(stringBytes, StandardCharsets.UTF_8);
    }
}
