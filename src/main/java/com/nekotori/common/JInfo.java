package com.nekotori.common;

import com.nekotori.entity.message.Message;
import com.nekotori.entity.message.MessageModel;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "server")
public class JInfo {

    public static ChannelFuture echo(ChannelHandlerContext ctx, String info){
        /*没什么意义，简化代码量*/
        log.info(info);
        Message message = MessageModel.builder().fromUser("server").toUser(" ").message(info).build();
       return ctx.writeAndFlush(message);

    }

    public static ChannelFuture echo(ChannelHandlerContext ctx, String info, boolean withLog){
        /*没什么意义，简化代码量*/
        if(withLog) log.info(info);
        return ctx.writeAndFlush(Unpooled.copiedBuffer(info, CharsetUtil.UTF_8));

    }
}
