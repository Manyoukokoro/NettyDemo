package com.nekotori.room;

import com.nekotori.user.User;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class Room implements Serializable {
    @NonNull
    @Getter
    @Setter
    private String id;

    private Map<String,String> message = new HashMap<String, String>();

    public Boolean writeMessage(String userName, String msg){
        try {
            message.put(userName, msg);
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public String getAndDeleteMessageByUser(String userId){
            return message.remove(userId);
    }


}
