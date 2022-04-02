package pro.prysm.orion.common.net.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import pro.prysm.orion.api.message.Message;
import pro.prysm.orion.api.protocol.PacketState;
import pro.prysm.orion.common.AbstractOrion;
import pro.prysm.orion.common.event.events.IncomingPacketEvent;
import pro.prysm.orion.common.net.Connection;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.Handler;
import pro.prysm.orion.common.protocol.PacketRegistry;
import pro.prysm.orion.common.protocol.incoming.IncomingPacket;

import java.lang.reflect.Method;
import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {
    private final ChannelHandler channelHandler;
    private final PacketRegistry registry;

    public PacketDecoder(ChannelHandler channelHandler) {
        this.channelHandler = channelHandler;
        this.registry = AbstractOrion.getProtocol().getPacketRegistry();
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        Connection connection = channelHandler.getConnection(ctx.channel().remoteAddress());
        if (!connection.isActive()) return; // Don't bother reading the packet if the connection isn't active
        PacketState state = connection.getState(); // Get the state of this connection

        PacketByteBuf buf = new PacketByteBuf(byteBuf);
        int id = buf.readVarInt();

        if (registry.getIncoming(state, id) != null) {
            AbstractOrion.getLogger().trace("Received packet with ID 0x{} and state: {}", Integer.toHexString(id).toUpperCase(), state);
            Class<? extends IncomingPacket> packetClass = registry.getIncoming(state, id);
            if (packetClass != null && packetClass != IncomingPacket.class) {
                IncomingPacket packet = (IncomingPacket) packetClass.getConstructors()[0].newInstance();
                packet.read(buf);

                Handler handler = connection.getHandler();
                Method handleMethod = handler.getClass().getMethod("handle", packetClass);
                handleMethod.invoke(handler, packet);

                AbstractOrion.getEventBus().post(new IncomingPacketEvent(), packet);
            }
        } else {
            connection.disconnect(new Message(String.format("<red>Invalid packet ID: 0x%s</red>", Integer.toHexString(id).toUpperCase())).toComponent());
        }
    }
}
