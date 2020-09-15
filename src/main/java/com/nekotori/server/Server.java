package com.nekotori.server;

import com.nekotori.entity.room.RoomModel;
import com.nekotori.entity.user.UserModel;
import com.nekotori.entity.user.UserList;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import lombok.Getter;
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
     @Getter
     private final int port =8082;

     volatile UserList userData = new UserList();
     volatile RoomModel chatRoom;


    public static void main(String[] args) throws Exception {

        final Server server = new Server();

        server.userData.addUser(new UserModel("dengjie","114514"));
        server.userData.addUser(new UserModel("zhanglan","1223334"));
        server.userData.addUser(new UserModel("nanjinwen","66666666"));
        server.chatRoom = new RoomModel("12333");

        EventLoopGroup acceptorGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap bootstrap = new MyBootstrap(acceptorGroup, workerGroup).generateBootstrap();
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline().addLast("ServerHandler",new ServerHandler(server.userData, server.chatRoom));
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind(server.getPort()).sync();
            log.info("Server started!");
            channelFuture.channel().closeFuture().sync();
        }finally {
            /*退出两个Group*/
            acceptorGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
