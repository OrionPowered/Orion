package pro.prysm.orion.common.net.pipeline;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.ReadTimeoutException;
import pro.prysm.orion.server.Orion;
import pro.prysm.orion.server.util.ExceptionHandler;

public class NettyExceptionHandler extends ChannelDuplexHandler {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if (cause instanceof ReadTimeoutException) {
            Orion.getLogger().info("{} timed out", ctx.channel().remoteAddress());
        } else if (cause != null) {
            ExceptionHandler.error(cause);
        }
    }
}
