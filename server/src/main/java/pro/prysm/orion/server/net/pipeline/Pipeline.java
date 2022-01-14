package pro.prysm.orion.server.net.pipeline;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.Getter;
import pro.prysm.orion.server.protocol.Protocol;

@Getter
public class Pipeline extends ChannelInitializer<SocketChannel> {
    private final ChannelHandler channelHandler;

    public Pipeline(Protocol protocol) {
        channelHandler = new ChannelHandler(protocol);
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(Protocol.LENGTH_DECODER, new PacketLengthDecoder());
        pipeline.addLast("read-timeout", new ReadTimeoutHandler(10));
        pipeline.addLast(Protocol.LENGTH_ENCODER, new PacketLengthEncoder());
        pipeline.addLast(Protocol.DECODER, new PacketDecoder(channelHandler));
        pipeline.addLast(Protocol.ENCODER, new PacketEncoder());
        pipeline.addLast(channelHandler);
        pipeline.addLast(new NettyExceptionHandler());
    }
}
