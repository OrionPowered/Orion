package pro.prysm.orion.api.protocol;

/**
 * @author 254n_m
 * @since 12/21/21 / 12:38 AM
 * This file was created as a part of Orion
 */
public interface Packet {
    PacketDirection getDirection();

    PacketState getState();

    int getId();
}
