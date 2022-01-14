package pro.prysm.orion.server.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Getter;
import pro.prysm.orion.api.event.event.ServerReadyEvent;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.net.pipeline.Pipeline;
import pro.prysm.orion.server.protocol.Protocol;
import pro.prysm.orion.server.util.ExceptionHandler;
import pro.prysm.orion.server.util.OrionThreadFactory;

import java.net.InetSocketAddress;

@Getter
public class TCPListener {
    private final Pipeline pipeline;
    private final InetSocketAddress address;
    private final int threads;

    public TCPListener(Protocol protocol, InetSocketAddress address, int threads) {
        this.address = address;
        this.threads = threads;
        pipeline = new Pipeline(protocol);
    }

    public void listen() throws InterruptedException {
        Orion.getLogger().info("Starting listener on {}", address);
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
                if (channelFuture.isSuccess()) Orion.getEventBus().post(new ServerReadyEvent());
                else {
                    Orion.getLogger().error("Failed to listen on {}", address);
                    throw new InterruptedException("Failed to listen on " + address);
                }
            });
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            ExceptionHandler.error(e);
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
