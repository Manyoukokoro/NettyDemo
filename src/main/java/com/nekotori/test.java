package com.nekotori;

import com.nekotori.entity.room.RoomModel;

public class test {


    public static void main(String[] args) {
        RoomModel testRoom = new RoomModel("2333");
        testRoom.writeMessage("1","dsdsddsd");
        System.out.println(testRoom.getAndDeleteMessageByUser("1"));

    }
}
