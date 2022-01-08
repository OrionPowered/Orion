package pro.prysm.orion.api.net;

import io.netty.channel.ChannelHandlerContext;
import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.api.protocol.outgoing.OutgoingPacket;

import java.net.SocketAddress;

/**
 * @author 254n_m
 * @since 12/21/21 / 12:46 AM
 * This file was created as a part of Orion
 */
public interface Connection {
    /**
     * Retrieves the channel handler context.
     * @return The contet
     */
    ChannelHandlerContext getCtx();

    /**
     * Retrieves the address which this connection is linked to.
     * @return The address
     */
    SocketAddress getAddress();

    /**
     * Retrieves the state that this connection is in.
     * @return The state
     */
    PacketState getState();

    boolean isActive();

    /**
     * Disconnects this connection from the server.
     * @param reason The reason for disconnecting
     */
    void disconnect(String reason);

    /**
     * Sends a packet to the other end of this connection.
     * @param packet The packet to send
     */
    void sendPacket(OutgoingPacket packet);
}
