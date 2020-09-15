package com.nekotori.common;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "server")
public class Info {

    public static ChannelFuture echo(ChannelHandlerContext ctx, String info){
        /*没什么意义，简化代码量*/
        log.info(info);
       return ctx.writeAndFlush(Unpooled.copiedBuffer(info, CharsetUtil.UTF_8));

    }

    public static ChannelFuture echo(ChannelHandlerContext ctx, String info, boolean withLog){
        /*没什么意义，简化代码量*/
        if(withLog) log.info(info);
        return ctx.writeAndFlush(Unpooled.copiedBuffer(info, CharsetUtil.UTF_8));

    }
}
