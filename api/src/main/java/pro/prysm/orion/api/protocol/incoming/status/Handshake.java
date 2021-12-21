package pro.prysm.orion.api.protocol.incoming.status;

import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.api.protocol.incoming.IncomingPacket;

/**
 * @author 254n_m
 * @since 12/21/21 / 1:12 AM
 * This file was created as a part of Orion
 */
public interface Handshake extends IncomingPacket {
    int getProtocolVersion();

    String getHostname();

    short getPort();

    PacketState getNextState();
}
