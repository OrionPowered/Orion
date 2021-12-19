package pro.prysm.orion.server.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.SocketAddress;
import java.util.HashMap;

public class ChannelHandler extends ChannelInboundHandlerAdapter {

    private final HashMap<Integer, Connection> connections;

    public ChannelHandler() {
        connections = new HashMap<>();
    }

    public HashMap<Integer, Connection> getConnections() {
        return connections;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        connections.put(ctx.channel().remoteAddress().hashCode(), new Connection(ctx));
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        connections.remove(ctx.channel().remoteAddress().hashCode());
    }

    public Connection getConnection(SocketAddress remoteAddress) {
        return connections.get(remoteAddress.hashCode());
    }

}
