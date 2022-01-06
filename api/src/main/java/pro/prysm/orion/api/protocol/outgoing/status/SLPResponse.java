package pro.prysm.orion.api.protocol.outgoing.status;

import pro.prysm.orion.api.protocol.ServerListResponse;
import pro.prysm.orion.api.protocol.outgoing.OutgoingPacket;

/**
 * @author 254n_m
 * @since 12/21/21 / 12:59 AM
 * This file was created as a part of Orion
 */
public interface SLPResponse extends OutgoingPacket {
    ServerListResponse getResponse();

    void setResponse(ServerListResponse response);
}
