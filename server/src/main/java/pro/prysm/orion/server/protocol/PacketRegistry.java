package pro.prysm.orion.server.protocol;

import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;
import pro.prysm.orion.server.protocol.incoming.status.*;
import pro.prysm.orion.server.protocol.incoming.login.*;
import pro.prysm.orion.server.protocol.incoming.play.*;

import java.util.HashMap;

public class PacketRegistry {

    HashMap<Integer, Class<? extends IncomingPacket>> incomingHandshake = new HashMap<>() {{
        put(0x00, Handshake.class);
    }};

    HashMap<Integer, Class<? extends IncomingPacket>> incomingStatus = new HashMap<>() {{
        put(0x00, IncomingPacket.class); // Empty packet
        put(0x01, Ping.class);
    }};

    HashMap<Integer, Class<? extends IncomingPacket>> incomingLogin = new HashMap<>() {{
        put(0x00, LoginStart.class);
        put(0x01, EncryptionResponse.class);
    }};

    HashMap<Integer, Class<? extends IncomingPacket>> incomingPlay = new HashMap<>() {{
        put(0x00, TeleportConfirm.class);
        put(0x05, ClientSettings.class);
        put(0x0A, PluginMessage.class);
        put(0x11, PlayerPosition.class);
        put(0x12, PlayerPositionAndRotation.class);
        put(0x13, PlayerRotation.class);
    }};

    public PacketRegistry() {}

    public Class<? extends IncomingPacket> getIncoming(PacketState state, int id) {
        Class<? extends IncomingPacket> packet = null;
        switch(state) {
            case HANDSHAKE -> packet = incomingHandshake.getOrDefault(id, null);
            case STATUS -> packet = incomingStatus.getOrDefault(id, null);
            case LOGIN -> packet = incomingLogin.getOrDefault(id, null);
            case PLAY -> packet = incomingPlay.getOrDefault(id, null);
        }
        return packet;
    }

}
