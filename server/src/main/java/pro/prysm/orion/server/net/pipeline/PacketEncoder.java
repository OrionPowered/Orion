package pro.prysm.orion.server.net.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.net.PacketByteBuf;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

public class PacketEncoder extends MessageToByteEncoder<OutgoingPacket> {
    @Override
    protected void encode(ChannelHandlerContext ctx, OutgoingPacket packet, ByteBuf byteBuf) {
        Orion.getLogger().debug("Sending packet {} to {}", packet.getClass().getSimpleName(), ctx.channel().remoteAddress());
        PacketByteBuf buf = new PacketByteBuf(byteBuf);
        packet.writeId(buf);
        packet.write(buf);
    }
}
