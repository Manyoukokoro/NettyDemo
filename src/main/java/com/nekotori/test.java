package com.nekotori;

import com.nekotori.room.Room;

public class test {
    public static void main(String[] args) {
        Room testRoom = new Room("2333");
        testRoom.writeMessage("1","dsdsddsd");
        System.out.println(testRoom.getAndDeleteMessageByUser("1"));

    }
}
