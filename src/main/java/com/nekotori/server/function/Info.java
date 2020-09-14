package com.nekotori.server.function;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

public class Info {

    public static ChannelFuture echo(ChannelHandlerContext ctx, String info){
       return ctx.writeAndFlush(Unpooled.copiedBuffer(info, CharsetUtil.UTF_8));
    }
}
