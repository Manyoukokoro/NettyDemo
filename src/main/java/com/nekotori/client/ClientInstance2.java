package com.nekotori.client;

import com.nekotori.entity.user.UserModel;

public class ClientInstance2 {
    public static void main(String[] args) throws Exception {
        UserModel user = new UserModel("dengjie","114514");
        new Client("0.0.0.0",8082,user).start();
    }
}
