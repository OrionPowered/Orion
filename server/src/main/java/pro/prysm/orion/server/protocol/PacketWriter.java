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
    protected int readVarInt(ByteBuf buf) {
        int value = 0;
        int length = 0;
        byte currentByte;
        while((value & 0x80) != 0x80) {
            currentByte = buf.readByte();
            value |= (currentByte & 0x7F) << (length * 7);
            length ++;
            if (length > 5) throw new RuntimeException("VarInt is too big");
        }
        return value;
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
}
