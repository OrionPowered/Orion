package pro.prysm.orion.api.net;

public interface PayloadBuffer {
    void writeVarInt(int value);

    int readVarInt();

    void writeVarLong(long value);

    long readVarLong();

    void writeString(String string);

    String readString();

    void writeByteArray(byte[] array);

    byte[] readByteArray();

    void writeIntArray(int[] array);

    int[] readIntArray();

    void writeLongArray(long[] array);

    long[] readLongArray();
}
