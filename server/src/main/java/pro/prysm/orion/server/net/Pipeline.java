package pro.prysm.orion.server.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.command.commands.SendPacketCommand;
import pro.prysm.orion.server.protocol.PacketRegistry;

public class Pipeline extends ChannelInitializer<SocketChannel> {
    private final Orion orion;
    public Pipeline(Orion orion) {
        this.orion = orion;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        PacketRegistry registry = new PacketRegistry();
        ChannelHandler channelHandler = new ChannelHandler();

        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("lengthDecoder", new PacketLengthDecoder());
        pipeline.addLast("decoder", new PacketDecoder(registry, channelHandler));
        pipeline.addLast("lengthEncoder", new PacketLengthEncoder());
        pipeline.addLast("encoder", new PacketEncoder());
        pipeline.addLast("timeout", new ReadTimeoutHandler(10));
        pipeline.addLast(channelHandler);
        orion.getCommandHandler().registerCommand(new SendPacketCommand(channelHandler));

    }
}
