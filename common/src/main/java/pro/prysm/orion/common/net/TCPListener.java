package pro.prysm.orion.common.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Getter;
import pro.prysm.orion.api.event.event.ServerReadyEvent;
import pro.prysm.orion.common.AbstractOrion;
import pro.prysm.orion.common.OrionExceptionHandler;
import pro.prysm.orion.common.OrionThreadFactory;
import pro.prysm.orion.common.net.pipeline.Pipeline;

import java.net.InetSocketAddress;

@Getter
public class TCPListener {
    private final Pipeline pipeline;
    private final InetSocketAddress address;
    private final int threads;

    public TCPListener(InetSocketAddress address, int threads) {
        this.address = address;
        this.threads = threads;
        pipeline = new Pipeline();
    }

    public void listen() throws InterruptedException {
        AbstractOrion.getLogger().info("Starting listener on {}", address);
        listen(address, threads);
    }

    private void listen(InetSocketAddress address, int threads) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup(threads, new OrionThreadFactory("netty"));
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.localAddress(address);
            bootstrap.childHandler(pipeline);
            ChannelFuture channelFuture = bootstrap.bind().sync();
            channelFuture.addListener((ChannelFutureListener) future -> {
                if (channelFuture.isSuccess()) AbstractOrion.getEventBus().post(new ServerReadyEvent());
                else {
                    AbstractOrion.getLogger().error("Failed to listen on {}", address);
                    throw new InterruptedException("Failed to listen on " + address);
                }
            });
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            OrionExceptionHandler.error(e);
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
