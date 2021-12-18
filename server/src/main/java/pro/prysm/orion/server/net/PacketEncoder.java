package pro.prysm.orion.server.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import pro.prysm.orion.server.protocol.outgoing.OutgoingPacket;

public class PacketEncoder extends MessageToByteEncoder<OutgoingPacket> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, OutgoingPacket packet, ByteBuf byteBuf) {
        packet.writeId(byteBuf);
        packet.write(byteBuf);
    }
}
