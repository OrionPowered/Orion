package pro.prysm.orion.server.net.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import pro.prysm.orion.server.net.PacketByteBuf;

import java.util.List;

public class PacketLengthDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) {
        PacketByteBuf in = new PacketByteBuf(byteBuf);
        in.markReaderIndex();
        int length = in.readVarInt();
        if (in.readableBytes() < length) in.resetReaderIndex();
        else out.add(in.readBytes(length));
    }
}