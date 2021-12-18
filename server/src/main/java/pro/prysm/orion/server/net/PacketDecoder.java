package pro.prysm.orion.server.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.event.events.IncomingPacketEvent;
import pro.prysm.orion.server.protocol.PacketWriter;
import pro.prysm.orion.server.protocol.incoming.Handshake;
import pro.prysm.orion.server.protocol.incoming.IncomingPacket;

import java.util.HashMap;
import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {
    // This is only a temporary solution, eventually this should be moved to its own class or something
    HashMap<Integer, Class<? extends IncomingPacket>> incomingPackets = new HashMap<>() {{
        put(0x00, Handshake.class);
    }};

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int id = PacketWriter.readVarInt(byteBuf);
        if (incomingPackets.containsKey(id)) {
            Class<? extends IncomingPacket> packetClass = incomingPackets.get(id);
            IncomingPacket packet = (IncomingPacket) packetClass.getConstructors()[0].newInstance();
            packet.read(byteBuf);
            Orion.EVENT_BUS.post(new IncomingPacketEvent(), packet);
        } else {
            channelHandlerContext.flush();
            channelHandlerContext.close();
        }
    }
}
