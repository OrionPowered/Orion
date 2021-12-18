package pro.prysm.orion.server.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class Pipeline extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("encoder", new PacketEncoder());
        pipeline.addLast("decoder", new PacketDecoder());
        pipeline.addLast("timeout", new ReadTimeoutHandler(10));
    }
}
