package pro.prysm.orion.server.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.event.events.IncomingPacketEvent;
import pro.prysm.orion.server.protocol.PacketRegistry;
import pro.prysm.orion.server.protocol.PacketWriter;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {
    private final PacketRegistry registry;
    private final ChannelHandler channelHandler;
    public PacketDecoder(PacketRegistry registry, ChannelHandler channelHandler) {
        this.channelHandler = channelHandler;
        this.registry = registry;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        Connection connection = channelHandler.getConnection(ctx.channel().remoteAddress());
        if (!connection.isActive()) return;
        PacketState state = connection.getState();

        int id = PacketWriter.readVarInt(byteBuf);
        if (registry.getIncoming(state, id) != null) {
            Orion.getLogger().finer(String.format("Received packet with ID %d and state: %s", id, state));
            Class<? extends IncomingPacket> packetClass = registry.getIncoming(state, id);
            if (packetClass != null && packetClass != IncomingPacket.class) {
                IncomingPacket packet = (IncomingPacket) packetClass.getConstructors()[0].newInstance(connection);
                packet.read(byteBuf);
                Orion.EVENT_BUS.post(new IncomingPacketEvent(), packet);
            }
        } else {
            connection.disconnect(String.format("<red>Invalid packet ID: %d</red>", id));
        }
    }
}
