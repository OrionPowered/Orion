package pro.prysm.orion.common.net.pipeline;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.ReadTimeoutException;
import pro.prysm.orion.common.AbstractOrion;
import pro.prysm.orion.common.OrionExceptionHandler;

public class NettyExceptionHandler extends ChannelDuplexHandler {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if (cause instanceof ReadTimeoutException) {
            AbstractOrion.getLogger().info("{} timed out", ctx.channel().remoteAddress());
        } else if (cause != null) {
            OrionExceptionHandler.error(cause);
        }
    }
}
