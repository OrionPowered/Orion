package pro.prysm.orion.server.net.pipeline;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.ReadTimeoutException;
import pro.prysm.orion.server.Orion;

public class ExceptionHandler extends ChannelDuplexHandler {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause instanceof ReadTimeoutException) {
             Orion.getLogger().info(String.format("%s timed out", ctx.channel().remoteAddress()));
        } else {
            super.exceptionCaught(ctx, cause);
        }
    }
}
