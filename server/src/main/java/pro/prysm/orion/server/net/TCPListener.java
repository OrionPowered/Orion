package pro.prysm.orion.server.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.event.events.ServerReadyEvent;

import java.net.InetSocketAddress;
import java.util.logging.Level;

public class TCPListener {
    private final Orion orion;
    public TCPListener(Orion orion) {
        this.orion = orion;
        try {
            listen(new InetSocketAddress("localhost", 25565));
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
            Orion.EVENT_BUS.post(new ServerReadyEvent());
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
