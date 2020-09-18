package com.nekotori.entity.message;

import java.io.Serializable;

public interface Message extends Serializable {
    String getSender();
    String getRecipient();
    String getBody();
}
