package com.nekotori;


import com.nekotori.code.MyDecoder;
import com.nekotori.code.MyDecoderImpl;
import com.nekotori.code.MyEncoder;
import com.nekotori.code.MyEncoderImpl;
import com.nekotori.common.Exception.MessageFormatException;
import com.nekotori.entity.message.Message;
import com.nekotori.entity.message.MessageModel;

public class test {


//    public static void main(String[] args) {
//        RoomModel testRoom = new RoomModel("2333");
//        testRoom.writeMessage("1","dsdsddsd");
//        System.out.println(testRoom.getAndDeleteMessageByUser("1"));
//
//    }

    public static String bytes_String16(byte[] b) {
        char[] _16 = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i<b.length;i++) {
            sb.append("0x")
                    .append(_16[b[i]>>4&0xf])
                    .append(_16[b[i]&0xf]).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws MessageFormatException {
        MyEncoder myEncoder = new MyEncoderImpl();
        Message message = MessageModel.builder().fromUser("this is sender").toUser("this is recipient").message("hello world!").build();
        byte[] info = myEncoder.encode0(message);
        for (Byte b:
             info) {
            System.out.println(b.byteValue());
        }
        System.out.println(bytes_String16(info));

        MyDecoder myDecoder = new MyDecoderImpl();

        Message message1 = myDecoder.decode0(info);
        System.out.println(message1.getSender());
        System.out.println(message1.getRecipient());
        System.out.println(message1.getBody());

    }
}
