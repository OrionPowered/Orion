package pro.prysm.orion.api.protocol.outgoing.login;

import pro.prysm.orion.api.protocol.outgoing.OutgoingPacket;

import java.util.UUID;

public interface LoginSuccess extends OutgoingPacket {
    String getUsername();
    UUID getUniqueId();
}
