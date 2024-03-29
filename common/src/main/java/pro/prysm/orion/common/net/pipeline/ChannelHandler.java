package pro.prysm.orion.common.net.pipeline;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOption;
import lombok.RequiredArgsConstructor;
import pro.prysm.orion.common.net.Connection;

import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@ChannelHandler.Sharable
public class ChannelHandler extends ChannelInboundHandlerAdapter {
    private final Map<Integer, Connection> connections = new ConcurrentHashMap<>();

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        ctx.channel().config().setOption(ChannelOption.TCP_NODELAY, true);
        connections.put(ctx.channel().remoteAddress().hashCode(), new Connection(ctx));
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        Connection connection = connections.remove(ctx.channel().remoteAddress().hashCode());
        connection.setActive(false);
        connection.getHandler().onDisconnect();
    }

    public Connection getConnection(SocketAddress remoteAddress) {
        return connections.get(remoteAddress.hashCode());
    }
}
