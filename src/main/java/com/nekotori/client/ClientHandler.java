package com.nekotori.client;

import com.nekotori.user.User;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j(topic = "Client")
@RequiredArgsConstructor
@NoArgsConstructor
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @NonNull
    private User user;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf)msg;
        log.info("received: "+in.toString(CharsetUtil.UTF_8));
//        Scanner scanner = new Scanner(System.in);
//        ctx.writeAndFlush(Unpooled.copiedBuffer( user.getName()+":"+scanner.nextLine().trim(),CharsetUtil.UTF_8));
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
         ChannelFuture cf = ctx.writeAndFlush(Unpooled.copiedBuffer("Connection Established!"+user.getName(),
                                                                                    CharsetUtil.UTF_8));
         if(cf.isSuccess()) {
             log.info("Connect to server success! ");
             log.info("login as:"+user.getName());
         }
         else
         log.info("message write: failed");

    }
}
