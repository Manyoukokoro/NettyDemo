package com.nekotori.client;

import com.nekotori.user.User;

public class ClientInstant2 {
    public static void main(String[] args) throws Exception {
        User user = new User("dengjie","114514");
        new Client("0.0.0.0",8082,user).start();
    }
}
