package pro.prysm.orion.common.protocol;

import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.common.protocol.incoming.IncomingPacket;
import pro.prysm.orion.common.protocol.incoming.handshaking.Handshake;
import pro.prysm.orion.common.protocol.incoming.login.EncryptionResponse;
import pro.prysm.orion.common.protocol.incoming.login.LoginStart;
import pro.prysm.orion.common.protocol.incoming.status.Ping;
import pro.prysm.orion.common.protocol.incoming.status.Request;

import java.util.HashMap;

public class PacketRegistry {
    HashMap<Integer, Class<? extends IncomingPacket>> incomingHandshake = new HashMap<>() {{
        put(0x00, Handshake.class);
    }};

    HashMap<Integer, Class<? extends IncomingPacket>> incomingStatus = new HashMap<>() {{
        put(0x00, Request.class);
        put(0x01, Ping.class);
    }};

    HashMap<Integer, Class<? extends IncomingPacket>> incomingLogin = new HashMap<>() {{
        put(0x00, LoginStart.class);
        put(0x01, EncryptionResponse.class);
    }};

    HashMap<Integer, Class<? extends IncomingPacket>> incomingPlay = new HashMap<>() {{
        // Incoming Play Packets
    }};

    public Class<? extends IncomingPacket> getIncoming(PacketState state, int id) {
        Class<? extends IncomingPacket> packet = null;
        switch (state) {
            case HANDSHAKE -> packet = incomingHandshake.getOrDefault(id, null);
            case STATUS -> packet = incomingStatus.getOrDefault(id, null);
            case LOGIN -> packet = incomingLogin.getOrDefault(id, null);
            case PLAY -> packet = incomingPlay.getOrDefault(id, null);
        }
        return packet;
    }

    public PacketRegistry registerIncoming(PacketState state, int id,  Class<? extends IncomingPacket> packet) {
        switch (state) {
            case HANDSHAKE -> incomingHandshake.put(id, packet);
            case STATUS -> incomingStatus.put(id, packet);
            case LOGIN -> incomingLogin.put(id, packet);
            case PLAY -> incomingPlay.put(id, packet);
        }
    }
}



