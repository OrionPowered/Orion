package pro.prysm.orion.server.net.pipeline;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import pro.prysm.orion.server.protocol.Protocol;

public class Pipeline extends ChannelInitializer<SocketChannel> {
    private final ChannelHandler channelHandler;
    private final Protocol protocol;
    public Pipeline(Protocol protocol) {
        this.protocol = protocol;
        channelHandler = new ChannelHandler();
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("lengthDecoder", new PacketLengthDecoder());
        pipeline.addLast("decoder", new PacketDecoder(protocol, channelHandler));
        pipeline.addLast("lengthEncoder", new PacketLengthEncoder());
        pipeline.addLast("encoder", new PacketEncoder());
        pipeline.addLast("timeout", new ReadTimeoutHandler(10));
        pipeline.addLast(channelHandler);
        // TODO: orion.getCommandHandler().registerCommand(new SendPacketCommand(channelHandler));
    }

    public ChannelHandler getChannelHandler() {
        return channelHandler;
    }

}
