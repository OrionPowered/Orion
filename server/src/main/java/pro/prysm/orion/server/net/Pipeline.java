package pro.prysm.orion.server.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class Pipeline extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {

        ChannelHandler channelHandler = new ChannelHandler();

        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("lengthDecoder", new PacketLengthDecoder());
        pipeline.addLast("decoder", new PacketDecoder(channelHandler));
        pipeline.addLast("lengthEncoder", new PacketLengthEncoder());
        pipeline.addLast("encoder", new PacketEncoder());
        pipeline.addLast("timeout", new ReadTimeoutHandler(10));
        pipeline.addLast(channelHandler);
    }
}
