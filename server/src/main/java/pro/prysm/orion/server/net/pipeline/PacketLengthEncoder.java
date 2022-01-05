package pro.prysm.orion.server.net.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import pro.prysm.orion.server.net.PacketByteBuf;
public class PacketLengthEncoder extends MessageToByteEncoder<ByteBuf> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf in, ByteBuf out) {
        PacketByteBuf buf = new PacketByteBuf(out);
        buf.writeVarInt(in.readableBytes());
        buf.writeBytes(in);
    }
}