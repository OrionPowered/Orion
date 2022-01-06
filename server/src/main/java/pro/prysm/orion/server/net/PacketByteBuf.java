package pro.prysm.orion.server.net;

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

public class PacketByteBuf extends AbstractPacketByteBuf {
    public PacketByteBuf(ByteBuf parent) {
        super(parent);
    }

    /**
     * Writes a VarInt to the Byte Buffer.
     * See more: <a href="https://wiki.vg/Data_types">wiki.vg/Data_types</a>
     *
     * @param value Integer to write
     */
    public void writeVarInt(int value) {
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
     * @return Integer
     */
    public int readVarInt() {
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
     * Writes a long to the Byte Buffer.
     * See more: <a href="https://wiki.vg/Data_types">wiki.vg/Data_types</a>
     *
     * @param value Long to write
     */
    public void writeVarLong(long value) {
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
     * @return Long
     */
    public long readVarLong() {
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
     */
    public void writeString(String string) {
        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        writeVarInt(bytes.length);
        buf.writeBytes(bytes);
    }

    /**
     * Reads a String from the Byte Buffer
     *
     * @return String
     */
    public String readString() {
        byte[] bytes = new byte[readVarInt()];
        buf.readBytes(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * Writes a byte array to the Byte Buffer
     *
     * @param array Byte array to write
     */
    public void writeByteArray(byte[] array) {
        writeVarInt(array.length);
        buf.writeBytes(array);
    }

    /**
     * Reads a byte array from the Byte Buffer
     *
     * @return byte[]
     */
    public byte[] readByteArray() {
        byte[] array = new byte[readVarInt()];
        buf.readBytes(array);
        return array;
    }

    /**
     * Writes an int array to the Byte Buffer
     *
     * @param array Int array to write
     */
    public void writeIntArray(int[] array) {
        writeVarInt(array.length);
        for (int i : array) buf.writeInt(i);
    }

    /**
     * Reads an int array from the Byte Buffer
     *
     * @return int[]
     */
    public int[] readIntArray() {
        int[] array = new int[readVarInt()];
        for (int i = 0; i < array.length; i++) array[i] = buf.readInt();
        return array;
    }

    /**
     * Writes a long array to the Byte Buffer
     *
     * @param array Long array to write
     */
    public void writeLongArray(long[] array) {
        writeVarInt(array.length);
        for (long i : array) buf.writeLong(i);
    }

    /**
     * Reads a long array from the Byte Buffer
     *
     * @return long[]
     */
    protected long[] readLongArray() {
        long[] array = new long[readVarInt()];
        for (int i = 0; i < array.length; i++) array[i] = buf.readLong();
        return array;
    }

    /**
     * Writes a UUID to the Byte Buffer
     * See more: <a href="https://wiki.vg/Data_types">wiki.vg/Data_types</a>
     *
     * @param uuid UUID to write
     */
    public void writeUuidIntArray(UUID uuid) {
        buf.writeInt((int) (uuid.getMostSignificantBits() >> 32));
        buf.writeInt((int) uuid.getMostSignificantBits());
        buf.writeInt((int) (uuid.getLeastSignificantBits() >> 32));
        buf.writeInt((int) uuid.getLeastSignificantBits());
    }

    /**
     * Writes a CompoundTag (NBT) to the Byte Buffer
     *
     * @param tag NBT to write
     */
    public void writeNBT(CompoundBinaryTag tag) {
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
     * Writes a Location object to the Byte Buffer
     *
     * @param location Location to deserialize and write
     */
    public void writeLocation(Location location) {
        buf.writeDouble(location.getX());
        buf.writeDouble(location.getY());
        buf.writeDouble(location.getZ());
        buf.writeFloat(location.getYaw());
        buf.writeFloat(location.getPitch());
    }
}
