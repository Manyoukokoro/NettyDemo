package com.nekotori.server.function;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@RequiredArgsConstructor
@Slf4j
public class PackageChecker {

    @NonNull
    private String message;
    private String[] messageDivided = new String[3];

    @Getter
    private boolean isValid = false;


    public PackageChecker check(){
        String[] tempMessage = message.split(":",3);
        if(tempMessage.length==3){
            PackageChecker packageCheck = new PackageChecker(message);
            packageCheck.messageDivided = Arrays.copyOf(tempMessage,3);
            packageCheck.isValid = true;
            return  packageCheck;
        }
        return new PackageChecker(message);
    }

    public String[] getMessage(){
        return messageDivided;
    }

}
