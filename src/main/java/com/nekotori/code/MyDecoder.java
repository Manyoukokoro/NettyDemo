package com.nekotori.code;

import com.nekotori.common.Exception.MessageFormatException;
import com.nekotori.entity.message.Message;

public interface MyDecoder {

    Message decode0(byte[] bytes) throws MessageFormatException;
}
