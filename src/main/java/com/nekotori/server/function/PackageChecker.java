package com.nekotori.server.function;

import com.nekotori.message.MessageModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@RequiredArgsConstructor
@Slf4j
public class PackageChecker {

    @NonNull
    private final String message;

    private MessageModel mModel;
//    private String[] messageDivided = new String[3];

    @Getter
    private boolean isValid = false;


    public PackageChecker check(){
        String[] tempMessage = message.split(":",3);
        if(tempMessage.length==3){
            PackageChecker packageCheck = new PackageChecker(message);
            packageCheck.mModel = MessageModel.builder()
                                                .fromUser(tempMessage[0])
                                                .toUser(tempMessage[1])
                                                .message(tempMessage[2])
                                                .build();
//            packageCheck.messageDivided = Arrays.copyOf(tempMessage,3);
            packageCheck.isValid = true;
            return  packageCheck;
        }
        return new PackageChecker(message);
    }

    public MessageModel getMessage(){
        return mModel;
    }

}
