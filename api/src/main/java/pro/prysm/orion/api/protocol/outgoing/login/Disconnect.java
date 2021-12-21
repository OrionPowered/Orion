package pro.prysm.orion.api.protocol.outgoing.login;

import pro.prysm.orion.api.chat.Message;
import pro.prysm.orion.api.protocol.outgoing.OutgoingPacket;

/**
 * @author 254n_m
 * @since 12/21/21 / 1:08 AM
 * This file was created as a part of Orion
 */
public interface Disconnect extends OutgoingPacket {
    Message getMessage();
}
