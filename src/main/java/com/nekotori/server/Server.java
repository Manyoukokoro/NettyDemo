package com.nekotori.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * auther: nekotori
 */
@Slf4j(topic = "Server")
public class Server {
    /**
     * 希望程序没事
     */
    static int port =8082;
    public static void main(String[] args) throws Exception {

        EventLoopGroup acceptorGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap bootstrap = new MyBootstrap(acceptorGroup, workerGroup).generateBootstrap();
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline().addLast(new ServerHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind(Server.port).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            acceptorGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
