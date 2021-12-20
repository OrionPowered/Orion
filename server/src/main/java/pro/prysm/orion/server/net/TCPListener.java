package pro.prysm.orion.server.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.event.events.ServerReadyEvent;

import java.net.InetSocketAddress;

public class TCPListener {
    private final Orion orion;
    public TCPListener(Orion orion, InetSocketAddress address) {
        this.orion = orion;
        try {
            Orion.getLogger().info(String.format("Starting listener on %s", address));
            listen(address);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void listen(InetSocketAddress address) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.localAddress(address);
            bootstrap.childHandler(new Pipeline(orion));
            ChannelFuture channelFuture = bootstrap.bind().sync();
            channelFuture.addListener((ChannelFutureListener) future -> {
                if (channelFuture.isSuccess()) Orion.EVENT_BUS.post(new ServerReadyEvent());
                else Orion.getLogger().severe(String.format("Failed to listen on %s", address));
            });
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
