package com.nekotori.server;

import com.nekotori.user.User;
import com.nekotori.user.UserData;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * auther: nekotori
 */
@Slf4j(topic = "Server")
@NoArgsConstructor
public class Server {
    /**
     * 希望程序没事
     */
    static int port =8082;

    static UserData userData = new UserData();

    static {
        userData.addUser(new User("dengjie","114514"));
        userData.addUser(new User("zhanglan","444564"));
    }

    public static void main(String[] args) throws Exception {

        EventLoopGroup acceptorGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap bootstrap = new MyBootstrap(acceptorGroup, workerGroup).generateBootstrap();
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline().addLast(new ServerHandler(userData));
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind(Server.port).sync();
            log.info("Server started!");
            channelFuture.channel().closeFuture().sync();
        }finally {
            acceptorGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
