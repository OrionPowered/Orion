package pro.prysm.orion.common.net.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import pro.prysm.orion.common.AbstractOrion;
import pro.prysm.orion.common.net.PacketByteBuf;
import pro.prysm.orion.common.protocol.outgoing.OutgoingPacket;

public class PacketEncoder extends MessageToByteEncoder<OutgoingPacket> {
    @Override
    protected void encode(ChannelHandlerContext ctx, OutgoingPacket packet, ByteBuf byteBuf) {
        AbstractOrion.getLogger().trace("Sending packet {} (0x{}) to {}", packet.getClass().getSimpleName(), Integer.toHexString(packet.getId()).toUpperCase(), ctx.channel().remoteAddress());
        PacketByteBuf buf = new PacketByteBuf(byteBuf);
        packet.writeId(buf);
        packet.write(buf);
    }
}
