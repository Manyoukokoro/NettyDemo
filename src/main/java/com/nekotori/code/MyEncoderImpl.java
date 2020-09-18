package com.nekotori.code;

import com.nekotori.entity.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

import java.util.Arrays;

import static com.nekotori.code.CodeConstants.END_OF_MESSAGE;
import static com.nekotori.code.CodeConstants.GAP;

public class MyEncoderImpl extends MessageToByteEncoder implements MyEncoder {



    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        Message message = (Message)o;
        byte[] bytes = encode0(message);
        byteBuf.writeBytes(bytes);
    }

    @Override
    public <T extends Message> byte[] encode0(T message) {
        byte[] senderByte= message.getSender().getBytes(CharsetUtil.UTF_8);
        byte[] recipientByte = message.getRecipient().getBytes(CharsetUtil.UTF_8);
        byte[] body = message.getBody().getBytes(CharsetUtil.UTF_8);
        byte length = (byte)body.length;

        ByteBuf bf = Unpooled.buffer();
        bf.writeBytes(CodeConstants.MAGIC_CODE).
                writeBytes(senderByte).
                writeBytes(GAP).
                writeBytes(recipientByte).
                writeBytes(GAP).
                writeByte(length).
                writeBytes(GAP).
                writeBytes(body).
                writeBytes(END_OF_MESSAGE);

        return Arrays.copyOfRange(bf.array(),0,bf.readableBytes());
    }
}
