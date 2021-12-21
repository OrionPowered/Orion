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
    ChannelHandlerContext getCtx();

    SocketAddress getAddress();

    PacketState getState();

    boolean isActive();

    void disconnect(String reason);

    void sendPacket(OutgoingPacket packet);
}
