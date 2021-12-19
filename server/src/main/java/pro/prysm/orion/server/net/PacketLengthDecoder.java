package pro.prysm.orion.server.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import pro.prysm.orion.server.protocol.Packet;

import java.util.List;

public class PacketLengthDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        in.markReaderIndex();
        int length = Packet.readVarInt(in);
        if (in.readableBytes() < length) in.resetReaderIndex();
        else out.add(in.readBytes(length));
    }
}