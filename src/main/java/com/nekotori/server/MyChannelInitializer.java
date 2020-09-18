package com.nekotori.server;

import com.nekotori.code.MyDecoderImpl;
import com.nekotori.code.MyEncoderImpl;
import com.nekotori.entity.room.RoomModel;
import com.nekotori.entity.user.UserList;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import lombok.AllArgsConstructor;

import java.net.Socket;

@AllArgsConstructor
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    UserList userData;
    RoomModel chatRoom;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        socketChannel.pipeline().
                addLast(new MyEncoderImpl()).
                addLast(new MyDecoderImpl()).
                addLast("hanlder",new ServerHandler(userData,chatRoom));
    }
}
