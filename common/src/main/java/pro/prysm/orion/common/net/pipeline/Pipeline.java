package pro.prysm.orion.common.net.pipeline;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.Getter;
import pro.prysm.orion.common.net.Channel;
import pro.prysm.orion.server.protocol.Protocol;

@Getter
public class Pipeline extends ChannelInitializer<SocketChannel> {
    private final ChannelHandler channelHandler;

    public Pipeline() {
        channelHandler = new ChannelHandler();
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(Channel.LENGTH_DECODER, new PacketLengthDecoder());
        pipeline.addLast(Channel.READ_TIMEOUT, new ReadTimeoutHandler(10));
        pipeline.addLast(Channel.LENGTH_ENCODER, new PacketLengthEncoder());
        pipeline.addLast(Channel.DECODER, new PacketDecoder(channelHandler));
        pipeline.addLast(Channel.ENCODER, new PacketEncoder());
        pipeline.addLast(channelHandler);
        pipeline.addLast(new NettyExceptionHandler());
    }
}
