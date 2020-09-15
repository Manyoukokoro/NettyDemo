package com.nekotori.server;

import com.nekotori.room.Room;
import com.nekotori.server.function.Info;
import com.nekotori.server.function.PackageChecker;
import com.nekotori.user.UserData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * author:nekotori
 * time:2020/9/14
 */

@Slf4j(topic = "Server")
@RequiredArgsConstructor
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @NonNull
    private final UserData userData;

    @NonNull
    private final Room chatRoom;

    Boolean connetionFlag = false ;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

            ByteBuf bf = (ByteBuf) msg;
            String smsg = bf.toString(CharsetUtil.UTF_8);
            log.info(smsg);
        if(connetionFlag) {
            try {

                PackageChecker packageChecker = new PackageChecker(smsg).check();
                if(packageChecker.isValid()) {
                    String fromUser = packageChecker.getMessage()[0];
                    String toUser = packageChecker.getMessage()[1];
                    String message = packageChecker.getMessage()[2];
                    /*如果发信人在库里找不到，就返回错误*/
                    if (null == userData.findUserById(fromUser)) throw new Exception("from user id not found!");

                    String tempmsg = chatRoom.getAndDeleteMessageByUser(userData.findUserById(fromUser).getName());
                    if (null != userData.findUserByName(toUser)) {
                        chatRoom.writeMessage(toUser, message);
                    } else {
                        Info.echo(ctx, "User not found!");
                    }

                    if (null != tempmsg) {
                        Info.echo(ctx, tempmsg);
                    } else {
                        Info.echo(ctx, "there's no message for you");
                    }
                }else{
                    Info.echo(ctx,"format error");
                }

            } catch (Exception e) {
                log.info(e.getMessage());
                Info.echo(ctx,"message format error!");
            }

        }else{
            connetionFlag = true;
            Info.echo(ctx, "waiting:");
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }
}
