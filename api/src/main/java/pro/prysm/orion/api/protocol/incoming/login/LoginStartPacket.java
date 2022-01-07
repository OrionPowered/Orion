package pro.prysm.orion.api.protocol.incoming.login;

import pro.prysm.orion.api.protocol.incoming.IncomingPacket;

/**
 * @author 254n_m
 * @since 12/21/21 / 1:14 AM
 * This file was created as a part of Orion
 */
public interface LoginStartPacket extends IncomingPacket {
    String getUsername();
}
