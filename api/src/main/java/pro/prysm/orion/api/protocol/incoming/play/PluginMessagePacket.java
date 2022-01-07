package pro.prysm.orion.api.protocol.incoming.play;

public interface PluginMessagePacket {
    String getChannel();

    byte[] getData();
}
