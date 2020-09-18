package com.nekotori.server;

import com.nekotori.common.KInfoKt;
import com.nekotori.entity.message.Message;
import com.nekotori.entity.message.MessageModel;
import com.nekotori.entity.room.RoomModel;
import com.nekotori.common.JInfo;
import com.nekotori.common.PackageChecker;
import com.nekotori.entity.user.UserModel;
import com.nekotori.entity.user.UserList;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sun.nio.ch.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author:nekotori
 * time:2020/9/14
 */

@Slf4j(topic = "Server")
@RequiredArgsConstructor
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @NonNull
    private final UserList userData;
    @NonNull
    private final RoomModel chatRoom;

    private UserModel connectedUser = null;

    Boolean connetionFlag = false ;

    Boolean scanMessage = true;

    ExecutorService executor;

    /**
     * ChannelRead方法，在channel中有字节写入时会被调用
     * 每次在channel中读取到数据时，先分析发件人
     * 如果发件人在数据库内能找到，就将收件人和消息体写入数据库
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message message = (Message) msg;
        if(connetionFlag) {
            try {

                    String sender = message.getSender();
                    String recipient = message.getRecipient();
                    String mess = message.getBody();

                    /*如果发信人在库里找不到，就返回错误*/
                    if (null == userData.findUserByName(sender)) throw new Exception("from user id not found!");

                    connectedUser = userData.findUserByName(sender);


                    if (null != userData.findUserByName(recipient)) {
                        chatRoom.writeMessage(recipient,"from "+sender+":"+mess);
                    } else {
                        JInfo.echo(ctx, "User not found!");
                    }

            } catch (Exception e) {
                JInfo.echo(ctx,"message format error!");
            }

        }else{
            connetionFlag = true;
            JInfo.echo(ctx,"waiting:");
        }
    }
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//
//            ByteBuf bf = (ByteBuf) msg;
//            String smsg = bf.toString(CharsetUtil.UTF_8);
//            log.info(smsg);
//        if(connetionFlag) {
//            try {
//
//                PackageChecker packageChecker = new PackageChecker(smsg).check();
//                if(packageChecker.isValid()) {
//                    MessageModel messageModel = packageChecker.getMessage();
//                    String fromUser = messageModel.getFromUser();
//                    String toUser = messageModel.getToUser();
//                    String message = messageModel.getMessage();
//
//                    /*如果发信人在库里找不到，就返回错误*/
//                    if (null == userData.findUserByName(fromUser)) throw new Exception("from user id not found!");
//
//                    connectedUser = userData.findUserByName(fromUser);
//
//
//                    if (null != userData.findUserByName(toUser)) {
//                        chatRoom.writeMessage(toUser,"from "+fromUser+":"+message);
//                    } else {
//                        JInfo.echo(ctx, "User not found!");
//                    }
//                }else{
//                    JInfo.echo(ctx,"format error");
//                }
//
//            } catch (Exception e) {
//                log.info(e.getMessage());
//                KInfoKt.echo(ctx,"message format error!");
//            }
//
//        }else{
//            connetionFlag = true;
//            KInfoKt.echo(ctx,"waiting:");
//        }
//    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        connetionFlag = false;
        scanMessage = false;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx,cause);
//        log.error("client shutdown!");
//        connetionFlag = false;
//        scanMessage = false;
    }


    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
    /*channel初始化时开一个线程，一直扫描chatroom，有信息就发给客户端*/
        executor = Executors.newSingleThreadExecutor();
        executor.submit(()-> {
                while(scanMessage) {
                    try {
                        if(connectedUser!= null){
                            String tempmsg = chatRoom.getAndDeleteMessageByUser(connectedUser.getName());
                            if (null != tempmsg) {
//                                Message message = MessageModel.builder().fromUser("server").message(tempmsg).build();
                                JInfo.echo(ctx, tempmsg);
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
        });


        /**
         * 单纯使用新建线程的方法会导致线程不可控
         * 推荐使用线程池
         */
//        Thread t = new Thread(()->{
//            while(scanMessage) {
//                    try {
//                        if(connectedUser!= null){
//                            String tempmsg = chatRoom.getAndDeleteMessageByUser(connectedUser.getName());
//                            if (null != tempmsg) {
//                                JInfo.echo(ctx, tempmsg,false);
//                            }
////                            else {
////                                Info.echo(ctx, "there's no message for you",false);
////                            }
//                        }
//                        Thread.sleep(500);
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//
//            }
//        });
//
//        t.start();
    }
}
