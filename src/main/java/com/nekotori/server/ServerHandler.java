package com.nekotori.server;

import com.nekotori.room.Room;
import com.nekotori.server.function.Info;
import com.nekotori.server.function.PackageChecker;
import com.nekotori.user.User;
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

    private User connectedUser = null;

    Boolean connetionFlag = false ;

    Boolean scanMessage = true;

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
                    if (null == userData.findUserByName(fromUser)) throw new Exception("from user id not found!");

                    connectedUser = userData.findUserByName(fromUser);


                    if (null != userData.findUserByName(toUser)) {
                        chatRoom.writeMessage(toUser,"from "+fromUser+":"+message);
                    } else {
                        Info.echo(ctx, "User not found!");
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
            Info.echo(ctx, "waiting:",false);
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
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
    /*channel初始化时开一个线程，一直扫描chatroom，有信息就发给客户端*/

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(scanMessage) {
                    try {
                        if(connectedUser!= null){
                            String tempmsg = chatRoom.getAndDeleteMessageByUser(connectedUser.getName());
                            if (null != tempmsg) {
                                Info.echo(ctx, tempmsg,false);
                            }
//                            else {
//                                Info.echo(ctx, "there's no message for you",false);
//                            }
                        }
                        Thread.sleep(500);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    }
                }
        });

        t.start();
    }
}
