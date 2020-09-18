package com.nekotori.entity.message;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 消息模板类
 */
@Data
@Builder
public class MessageModel implements Message{

    private String fromUser;
    private String toUser;
    private String message;


    @Override
    public String getSender() {
        return fromUser;
    }

    @Override
    public String getRecipient() {
        return toUser;
    }

    @Override
    public String getBody() {
        return message;
    }

//    public static class builder{
//        private MessageModel target;
//        public builder fromUser(String fromUser){
//            this.target.fromUser = fromUser;
//            return this;
//        }
//        public builder toUser(String toUser){
//            this.target.toUser = toUser;
//            return this;
//        }
//        public  builder message(String message){
//            this.target.message = message;
//            return this;
//        }
//        public MessageModel build(){
//            return this.target;
//        }
//
//    }
}
