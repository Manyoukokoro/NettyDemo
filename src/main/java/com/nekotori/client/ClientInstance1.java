package com.nekotori.client;

import com.nekotori.user.User;

public class ClientInstance1 {
    public static void main(String[] args) throws Exception {
        User user = new User("zhanglan","1223334");
        new Client("0.0.0.0",8082,user).start();
    }
}
