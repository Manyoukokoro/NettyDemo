package com.nekotori.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

@Slf4j(topic = "Client")
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf)msg;
        log.info("received: "+in.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelFuture cf = ctx.writeAndFlush(Unpooled.copiedBuffer("TestMessage", Charset.forName("UTF-8")));
        //判断写入是否成功，发现在写入的时候，只能给wirteAndFlush方法传入一个ByteBuf对象，传入字符串无法成功。
         log.info("message write:" + Boolean.toString(cf.isSuccess()));
         ChannelFuture cf2 = ctx.writeAndFlush(Unpooled.copiedBuffer("Hello world!", CharsetUtil.UTF_8));
         if(cf2.isSuccess())
         log.info("send to server success! "+"Hello world");
         else
         log.info("message write: failed");
    }
}
