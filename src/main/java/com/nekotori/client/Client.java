package com.nekotori.client;

import com.nekotori.code.MyDecoderImpl;
import com.nekotori.code.MyEncoderImpl;
import com.nekotori.entity.message.Message;
import com.nekotori.entity.message.MessageModel;
import com.nekotori.entity.user.UserModel;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@AllArgsConstructor
@Slf4j
public class Client {

    private final String host;
    private final int port;
    private final UserModel user;

    public void start() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .remoteAddress(host, port)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().
                                    addLast(new MyEncoderImpl()).
                                    addLast(new MyDecoderImpl()).
                                    addLast(new ClientHandler(user));
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect().sync();
            Channel ch = channelFuture.channel();
//            Channel ch = channelFuture.channel().closeFuture().sync().channel();
            Thread.sleep(500);
            Scanner scan = new Scanner(System.in);
            System.out.println("chat to whom?");
            String connectionInfo = scan.nextLine();
            Message m = MessageModel.builder().fromUser(user.getName()).
                    toUser(connectionInfo).
                    message("connection test").
                    build();
            System.out.println("waiting for response.");
            ch.writeAndFlush(m);
            while(true){

                ch.writeAndFlush(MessageModel.builder().
                        fromUser(user.getName()).
                        toUser(connectionInfo).
                        message(scan.nextLine()).
                        build());
            }

        }finally {
            group.shutdownGracefully();
        }

    }

}
