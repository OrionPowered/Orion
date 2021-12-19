package pro.prysm.orion.server.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import pro.prysm.orion.server.protocol.Packet;

public class PacketLengthEncoder extends MessageToByteEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        Packet.writeVarInt(msg.readableBytes(), out);
        out.writeBytes(msg);
    }
}