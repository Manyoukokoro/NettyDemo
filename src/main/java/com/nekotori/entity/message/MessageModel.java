package com.nekotori.entity.message;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 消息模板类
 */
@Data
@Builder
public class MessageModel implements Serializable {

    private String fromUser;
    private String toUser;
    private String message;

//    public static class builder{
//        private String bfromUser;
//        private String btoUser;
//        private String bmessage;
//        public builder fromUser(String fromUser){
//            bfromUser = fromUser;
//            return this;
//        }
//        public builder toUser(String toUser){
//            btoUser = toUser;
//            return this;
//        }
//        public  builder message(String message){
//            bmessage = message;
//            return this;
//        }
//        public MessageModel build(){
//            return new MessageModel(bfromUser,btoUser,bmessage);
//        }
//
//    }
}
