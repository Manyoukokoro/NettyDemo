package com.nekotori.room;

import com.nekotori.user.User;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

@RequiredArgsConstructor
@Data
public class Room implements Serializable {
    private User user_1;
    private User user_2;

    String[] infoBuff = new String[2];

    @NonNull
    private String roomId;

    public Boolean addMember(User user){
        if(user_1==null){
            user_1=user;
            return true;
        }else if(user_2==null){
            user_2=user;
            return true;
        }
        return false;
    }



}
