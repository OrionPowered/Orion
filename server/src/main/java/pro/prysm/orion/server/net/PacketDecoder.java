package pro.prysm.orion.server.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.event.events.IncomingPacketEvent;
import pro.prysm.orion.server.protocol.PacketWriter;
import pro.prysm.orion.server.protocol.incoming.status.Handshake;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;
import pro.prysm.orion.server.protocol.incoming.status.Ping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PacketDecoder extends ByteToMessageDecoder {
    HashMap<Integer, Class<? extends IncomingPacket>> handshakePackets = new HashMap<>() {{
        put(0x00, Handshake.class);
    }};

    HashMap<Integer, Class<? extends IncomingPacket>> statusPackets = new HashMap<>() {{
        put(0x00, null);
        put(0x01, Ping.class);
    }};

    private final ChannelHandler channelHandler;
    public PacketDecoder(ChannelHandler channelHandler) {
        this.channelHandler = channelHandler;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        Connection connection = channelHandler.getConnection(ctx.channel().remoteAddress());
        if (!connection.isActive()) return;
        PacketState state = connection.getState();

        Map<Integer, Class<? extends IncomingPacket>> packets = null;
        switch (state) {
            case HANDSHAKE -> packets = handshakePackets;
            case STATUS -> packets = statusPackets;
        }
        int id = PacketWriter.readVarInt(byteBuf);
        if (packets != null && packets.containsKey(id)) {
            Orion.getLogger().finer(String.format("Received packet with ID %d and state: %s", id, state));
            Class<? extends IncomingPacket> packetClass = packets.get(id);
            if (packetClass != null) {
                IncomingPacket packet = (IncomingPacket) packetClass.getConstructors()[0].newInstance(connection);
                packet.read(byteBuf);
                Orion.EVENT_BUS.post(new IncomingPacketEvent(), packet);
            }
        } else connection.disconnect(String.format("<red>Invalid packet ID: %d</red>", id));
    }
}
