package com.nekotori.code;

import com.nekotori.common.Exception.MessageFormatException;
import com.nekotori.entity.message.Message;
import com.nekotori.entity.message.MessageModel;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class MyDecoderImpl extends ByteToMessageDecoder implements MyDecoder {
    @Override
    public Message decode0(byte[] bytes) throws MessageFormatException {
        if(bytes.length<2||bytes[0]!=CodeConstants.MAGIC_CODE[0]||bytes[1]!=CodeConstants.MAGIC_CODE[1]){
            throw new MessageFormatException("bad message header or length");
        }
        if(bytes[bytes.length-3]!=CodeConstants.END_OF_MESSAGE[0]||
                bytes[bytes.length-2]!=CodeConstants.END_OF_MESSAGE[1]||
                bytes[bytes.length-1]!=CodeConstants.END_OF_MESSAGE[2])
            throw new MessageFormatException("bad end of message");
        ByteBuf bf = Unpooled.buffer();
        for(int i =2;i<bytes.length-3;i++){
            bf.writeByte(bytes[i]);
        }
        String s = bf.toString(CharsetUtil.UTF_8);
        String[] split = s.split("\r\n",4);
        Message message = MessageModel.builder().fromUser(split[0]).toUser(split[1]).message(split[3]).build();
        return message;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        byte [] content =new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(content);

        list.add(decode0(content));
    }
}
