package pro.prysm.orion.server.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import pro.prysm.orion.api.event.event.ServerReadyEvent;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.net.pipeline.Pipeline;
import pro.prysm.orion.server.protocol.Protocol;

import java.net.InetSocketAddress;

public class TCPListener {
    private final Pipeline pipeline;
    private final InetSocketAddress address;
    private final int threads;

    public TCPListener(Protocol protocol, InetSocketAddress address, int threads) {
        this.address = address;
        this.threads = threads;
        pipeline = new Pipeline(protocol);
    }

    public void listen() {
        try {
            Orion.getLogger().info(String.format("Starting listener on %s", address));
            listen(address, threads);
        } catch (InterruptedException e) {
            e.printStackTrace();
            // TODO: shutdown here
        }
    }

    private void listen(InetSocketAddress address, int threads) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup(threads);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.localAddress(address);
            bootstrap.childHandler(pipeline);
            ChannelFuture channelFuture = bootstrap.bind().sync();
            channelFuture.addListener((ChannelFutureListener) future -> {
                if (channelFuture.isSuccess()) Orion.getEventBus().post(new ServerReadyEvent());
                else Orion.getLogger().severe(String.format("Failed to listen on %s", address)); // TODO: shutdown here
            });
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public int getThreads() {
        return threads;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }
}
