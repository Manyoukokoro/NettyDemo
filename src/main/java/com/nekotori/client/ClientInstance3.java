package com.nekotori.client;

import com.nekotori.user.User;

public class ClientInstance3 {
    public static void main(String[] args) throws Exception {
        User user = new User("nanjinwen","66666666");
        new Client("0.0.0.0",8082,user).start();
    }
}
