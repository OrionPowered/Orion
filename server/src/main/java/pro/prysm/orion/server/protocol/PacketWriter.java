package pro.prysm.orion.server.protocol;

import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

// https://github.com/MiniDigger/MiniCraft/blob/4109c25ceac99e97a668269b73dacbac939c8e8d/src/main/java/me/minidigger/minicraft/protocol/DataTypes.java#L90
// https://wiki.vg/Data_types
public abstract class PacketWriter {
    /**
     * Writes a VarInt to the Byte Buffer.
     * See more: <a href="https://wiki.vg/Data_types">wiki.vg/Data_types</a>
     * @param value Integer to write
     * @param buf Buffer to write to
     */
    protected void writeVarInt(int value, ByteBuf buf) {
        while (value != 0) {
            if ((value & ~0x7F) == 0) {
                buf.writeByte(value);
                value = 0; // Break the while loop
            }
            buf.writeByte((value & 0x7F) | 0x80);
            // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
            value >>>= 7;
        }
    }

    /**
     * Reads a VarInt from the Byte Buffer.
     * See more: <a href="https://wiki.vg/Data_types">wiki.vg/Data_types</a>
     * @param buf Buffer to read from
     * @return Integer
     */
    public static int readVarInt(ByteBuf buf) {
        // This method was taken from MiniDigger/Minicraft (MIT)
        // https://github.com/MiniDigger/MiniCraft/blob/4109c25ceac99e97a668269b73dacbac939c8e8d/src/main/java/me/minidigger/minicraft/protocol/DataTypes.java#L48
        int numRead = 0;
        int result = 0;
        byte read;
        do {
            read = buf.readByte();
            int value = (read & 0b01111111);
            result |= (value << (7 * numRead));

            numRead++;
            if (numRead > 5) {
                throw new RuntimeException("VarInt is too big");
            }
        } while ((read & 0b10000000) != 0);

        return result;
    }

    /**
     * Writes a long to the Byte Buffer.
     * See more: <a href="https://wiki.vg/Data_types">wiki.vg/Data_types</a>
     * @param value Long to write
     * @param buf Buffer to write to
     */
    protected void writeVarLong(long value, ByteBuf buf) {
        while (value != 0) {
            if ((value & ~0x7F) == 0) {
                buf.writeByte((byte)value);
                value = 0; // Break the while loop
            }
            buf.writeByte((byte)(value & 0x7F) | 0x80);
            // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
            value >>>= 7;
        }
    }

    /**
     * Reads a VarLong from the Byte Buffer.
     * See more: <a href="https://wiki.vg/Data_types">wiki.vg/Data_types</a>
     * @param buf Buffer to read from
     * @return Long
     */
    protected long readVarLong(ByteBuf buf) {
        long value = 0;
        int length = 0;
        byte currentByte;
        while ((value & 0x80) != 0x80) {
            currentByte = buf.readByte();
            value |= (long) (currentByte & 0x7F) << (length * 7);
            length += 1;
            if (length > 10) throw new RuntimeException("VarLong is too big");
        }
        return value;
    }

    /**
     * Writes a String to the Byte Buffer
     * @param string String to write
     * @param buf Buffer to write to
     */
    protected void writeString(String string, ByteBuf buf) {
        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        writeVarInt(bytes.length, buf);
        buf.writeBytes(bytes);
    }

    /**
     * Reads a String from the Byte Buffer
     * @param buf Buffer to read from
     * @return String
     */
    protected String readString(ByteBuf buf) {
        byte[] bytes = new byte[readVarInt(buf)];
        buf.readBytes(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * Writes a byte array to the Byte Buffer
     * @param array Byte array to write
     * @param buf Buffer to write to
     */
    protected void writeByteArray(byte[] array, ByteBuf buf) {
        writeVarInt(array.length, buf);
        buf.writeBytes(array);
    }

    /**
     * Reads a byte array from the Byte Buffer
     * @param buf Buffer to read from
     * @return byte[]
     */
    protected byte[] readBytesArray(ByteBuf buf) {
        byte[] array = new byte[readVarInt(buf)];
        buf.readBytes(array);
        return array;
    }

    /**
     * Writes an int array to the Byte Buffer
     * @param array Int array to write
     * @param buf Buffer to write to
     */
    protected void writeIntArray(int[] array, ByteBuf buf) {
        writeVarInt(array.length, buf);
        for (int i : array) buf.writeInt(i);
    }

    /**
     * Reads an int array from the Byte Buffer
     * @param buf Buffer to read from
     * @return int[]
     */
    protected int[] readIntArray(ByteBuf buf) {
        int[] array = new int[readVarInt(buf)];
        for (int i = 0; i < array.length; i++) array[i] = buf.readInt();
        return array;
    }

    /**
     * Writes a long array to the Byte Buffer
     * @param array Long array to write
     * @param buf Buffer to write to
     */
    protected void writeLongArray(long[] array, ByteBuf buf) {
        writeVarInt(array.length, buf);
        for (long i : array) buf.writeLong(i);
    }

    /**
     * Reads a long array from the Byte Buffer
     * @param buf Buffer to read from
     * @return long[]
     */
    protected long[] readLongArray(ByteBuf buf) {
        long[] array = new long[readVarInt(buf)];
        for (int i = 0; i < array.length; i++) array[i] = buf.readLong();
        return array;
    }
}
