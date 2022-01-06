package pro.prysm.orion.server.protocol;

import io.netty.buffer.ByteBuf;
import net.kyori.adventure.nbt.BinaryTagIO;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import pro.prysm.orion.api.data.Location;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/*
A huge portion of this class can be credited to MiniDigger
https://github.com/MiniDigger/MiniCraft/blob/master/src/main/java/me/minidigger/minicraft/protocol/DataTypes.java

MiniDigger/MiniCraft
MIT License

Copyright (c) 2019 MiniDigger

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

public abstract class PacketWriter {
    /**
     * Writes a VarInt to the Byte Buffer.
     * See more: <a href="https://wiki.vg/Data_types">wiki.vg/Data_types</a>
     *
     * @param value Integer to write
     * @param buf   Buffer to write to
     */
    public static void writeVarInt(int value, ByteBuf buf) {
        do {
            byte temp = (byte) (value & 0b01111111);
            // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
            value >>>= 7;
            if (value != 0) {
                temp |= 0b10000000;
            }
            buf.writeByte(temp);
        } while (value != 0);
    }

    /**
     * Reads a VarInt from the Byte Buffer.
     * See more: <a href="https://wiki.vg/Data_types">wiki.vg/Data_types</a>
     *
     * @param buf Buffer to read from
     * @return Integer
     */
    public static int readVarInt(ByteBuf buf) {
        int length = 0;
        int result = 0;
        byte read;
        do {
            read = buf.readByte();
            result |= (read & 0x7F) << (7 * length);
            length++;
            if (length > 5) {
                throw new RuntimeException("VarInt is too big");
            }
        } while ((read & 0x80) != 0);
        return result;
    }

    /**
     * Writes a long array to the Byte Buffer
     *
     * @param array Long array to write
     * @param buf   Buffer to write to
     */
    public static void writeLongArray(long[] array, ByteBuf buf) {
        writeVarInt(array.length, buf);
        for (long i : array) buf.writeLong(i);
    }

    /**
     * Writes a UUID to the Byte Buffer
     * See more: <a href="https://wiki.vg/Data_types">wiki.vg/Data_types</a>
     *
     * @param uuid UUID to write
     * @param buf  Buffer to write to
     */
    public static void writeUuidIntArray(UUID uuid, ByteBuf buf) {
        buf.writeInt((int) (uuid.getMostSignificantBits() >> 32));
        buf.writeInt((int) uuid.getMostSignificantBits());
        buf.writeInt((int) (uuid.getLeastSignificantBits() >> 32));
        buf.writeInt((int) uuid.getLeastSignificantBits());
    }

    /**
     * Writes a CompoundTag (NBT) to the Byte Buffer
     *
     * @param tag NBT to write
     * @param buf Buffer to write to
     */
    public static void writeNBT(CompoundBinaryTag tag, ByteBuf buf) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        OutputStream out = new DataOutputStream(stream);
        try {
            BinaryTagIO.writer().write(tag, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buf.writeBytes(stream.toByteArray());
    }

    /**
     * Writes a long to the Byte Buffer.
     * See more: <a href="https://wiki.vg/Data_types">wiki.vg/Data_types</a>
     *
     * @param value Long to write
     * @param buf   Buffer to write to
     */
    protected void writeVarLong(long value, ByteBuf buf) {
        do {
            byte temp = (byte) (value & 0b01111111);
            // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
            value >>>= 7;
            if (value != 0) {
                temp |= 0b10000000;
            }
            buf.writeByte(temp);
        } while (value != 0);
    }

    /**
     * Reads a VarLong from the Byte Buffer.
     * See more: <a href="https://wiki.vg/Data_types">wiki.vg/Data_types</a>
     *
     * @param buf Buffer to read from
     * @return Long
     */
    protected long readVarLong(ByteBuf buf) {
        int numRead = 0;
        long result = 0;
        byte read;
        do {
            read = buf.readByte();
            int value = (read & 0b01111111);
            result |= (value << (7 * numRead));

            numRead++;
            if (numRead > 10) {
                throw new RuntimeException("VarLong is too big");
            }
        } while ((read & 0b10000000) != 0);

        return result;
    }

    /**
     * Writes a String to the Byte Buffer
     *
     * @param string String to write
     * @param buf    Buffer to write to
     */
    protected void writeString(String string, ByteBuf buf) {
        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        writeVarInt(bytes.length, buf);
        buf.writeBytes(bytes);
    }

    /**
     * Reads a String from the Byte Buffer
     *
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
     *
     * @param array Byte array to write
     * @param buf   Buffer to write to
     */
    protected void writeByteArray(byte[] array, ByteBuf buf) {
        writeVarInt(array.length, buf);
        buf.writeBytes(array);
    }

    /**
     * Reads a byte array from the Byte Buffer
     *
     * @param buf Buffer to read from
     * @return byte[]
     */
    protected byte[] readByteArray(ByteBuf buf) {
        byte[] array = new byte[readVarInt(buf)];
        buf.readBytes(array);
        return array;
    }

    /**
     * Writes an int array to the Byte Buffer
     *
     * @param array Int array to write
     * @param buf   Buffer to write to
     */
    protected void writeIntArray(int[] array, ByteBuf buf) {
        writeVarInt(array.length, buf);
        for (int i : array) buf.writeInt(i);
    }

    /**
     * Reads an int array from the Byte Buffer
     *
     * @param buf Buffer to read from
     * @return int[]
     */
    protected int[] readIntArray(ByteBuf buf) {
        int[] array = new int[readVarInt(buf)];
        for (int i = 0; i < array.length; i++) array[i] = buf.readInt();
        return array;
    }

    /**
     * Reads a long array from the Byte Buffer
     *
     * @param buf Buffer to read from
     * @return long[]
     */
    protected long[] readLongArray(ByteBuf buf) {
        long[] array = new long[readVarInt(buf)];
        for (int i = 0; i < array.length; i++) array[i] = buf.readLong();
        return array;
    }

    protected void writeLocation(Location location, ByteBuf buf) {
        buf.writeDouble(location.getX());
        buf.writeDouble(location.getY());
        buf.writeDouble(location.getZ());
        buf.writeFloat(location.getYaw());
        buf.writeFloat(location.getPitch());
    }
}
