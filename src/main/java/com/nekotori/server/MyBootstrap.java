package com.nekotori.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.util.Properties;

@Slf4j
@RequiredArgsConstructor
public class MyBootstrap {

    @NonNull
    EventLoopGroup acceptor;
    @NonNull
    EventLoopGroup worker;

//    private static final Properties conf = new Properties();
//
//    static {
//        try {
//            FileInputStream io = new FileInputStream("bootstrap.xml");
//            conf.loadFromXML(io);
//        }catch (Exception e){
//            log.info(e.getCause().toString());
//        }
//
//    }

    public ServerBootstrap generateBootstrap(){
        return new ServerBootstrap() .group(acceptor,worker)
                                                                .channel(NioServerSocketChannel.class)
                                                                .option(ChannelOption.SO_BACKLOG,128)
                                                                .childOption(ChannelOption.SO_KEEPALIVE,true);
    }
}
