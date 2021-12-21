package pro.prysm.orion.server.net.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

public class PacketEncoder extends MessageToByteEncoder<OutgoingPacket> {
    @Override
    protected void encode(ChannelHandlerContext ctx, OutgoingPacket packet, ByteBuf byteBuf) {
        Orion.getLogger().finer(String.format("Sending packet %s to %s", packet.getClass().getSimpleName(), ctx.channel().remoteAddress()));
        packet.writeId(byteBuf);
        packet.write(byteBuf);
    }
}
