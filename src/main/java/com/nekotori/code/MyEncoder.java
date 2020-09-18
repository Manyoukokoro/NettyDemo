package com.nekotori.code;


import com.nekotori.entity.message.Message;

public interface MyEncoder {

     <T extends Message> byte[] encode0(T message);
}
