package com.nekotori.client;

import com.nekotori.entity.user.UserModel;

public class ClientInstance3 {
    public static void main(String[] args) throws Exception {
        UserModel user = new UserModel("nanjinwen","66666666");
        new Client("0.0.0.0",8082,user).start();
    }
}
