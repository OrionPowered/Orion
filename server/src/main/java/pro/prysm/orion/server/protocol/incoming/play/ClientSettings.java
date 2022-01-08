package pro.prysm.orion.server.protocol.incoming.play;

import lombok.Getter;
import pro.prysm.orion.server.net.Connection;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

@Getter
public class ClientSettings extends IncomingPacket {
    private String locale;
    private int viewDistance;
    private byte chatMode;
    private boolean coloredChat;
    private short skinParts;
    private int mainHand;
    private boolean textFiltering;
    private boolean showInList;

    public ClientSettings(Connection connection) {
        super(0x05, connection);
    }

    @Override
    public void read(PacketByteBuf buf) {
        locale = buf.readString();
        viewDistance = buf.readByte();
        chatMode = buf.readByte();
        coloredChat = buf.readBoolean();
        skinParts = buf.readUnsignedByte();
        mainHand = buf.readVarInt();
        textFiltering = buf.readBoolean();
        showInList = buf.readBoolean();
        connection.getHandler().handle(this);
    }
}
