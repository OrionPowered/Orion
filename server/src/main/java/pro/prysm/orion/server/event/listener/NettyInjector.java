package pro.prysm.orion.server.event.listener;

import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.event.events.PacketEvent;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import pro.prysm.orion.server.protocol.Packet;

public class NettyInjector extends ChannelDuplexHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        PacketEvent.Incoming incoming = new PacketEvent.Incoming((Packet) msg);
        Orion.EVENT_BUS.post(incoming);
        if (incoming.isCancelled()) return;
        super.channelRead(ctx, incoming.getPacket());
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        PacketEvent.Outgoing outgoing = new PacketEvent.Outgoing((Packet) msg);
        Orion.EVENT_BUS.post(outgoing);
        if (outgoing.isCancelled()) return;
        super.write(ctx, outgoing.getPacket(), promise);
    }
}
