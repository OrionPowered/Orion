package pro.prysm.orion.common.net.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.RequiredArgsConstructor;
import pro.prysm.orion.api.message.Message;
import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.common.AbstractOrion;
import pro.prysm.orion.common.net.Connection;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.Protocol;
import pro.prysm.orion.common.protocol.incoming.IncomingPacket;

import pro.prysm.orion.common.event.events.IncomingPacketEvent;

import java.util.List;

@RequiredArgsConstructor
public class PacketDecoder extends ByteToMessageDecoder {
    private final ChannelHandler channelHandler;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        Connection connection = channelHandler.getConnection(ctx.channel().remoteAddress());
        if (!connection.isActive()) return; // Don't bother reading the packet if the connection isn't active
        PacketState state = connection.getState(); // Get the state of this connection

        PacketByteBuf buf = new PacketByteBuf(byteBuf);
        int id = buf.readVarInt();

        Protocol protocol = AbstractOrion.getProtocol();

        if (protocol.getPacketRegistry().getIncoming(state, id) != null) {
            AbstractOrion.getLogger().trace("Received packet with ID 0x{} and state: {}", Integer.toHexString(id).toUpperCase(), state);
            Class<? extends IncomingPacket> packetClass = protocol.getPacketRegistry().getIncoming(state, id);
            if (packetClass != null && packetClass != IncomingPacket.class) {
                IncomingPacket packet = (IncomingPacket) packetClass.getConstructors()[0].newInstance(connection);
                packet.read(buf);
                AbstractOrion.getEventBus().post(new IncomingPacketEvent(), packet);
            }
        } else {
            connection.disconnect(new Message(String.format("<red>Invalid packet ID: 0x%s</red>", Integer.toHexString(id).toUpperCase())).toComponent());
        }
    }
}
