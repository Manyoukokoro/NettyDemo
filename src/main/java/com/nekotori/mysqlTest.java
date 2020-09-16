package com.nekotori;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class mysqlTest {

    public static void main(String[] args) {
        String user = "root";
        String password = "1234abcd@";
        String url = "jdbc:mysql://www.nekotori.xyz:10003/nettyserver";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = null;
            connection = DriverManager.getConnection(url,user,password);
            String sql1 = "CREATE TABLE student " +
                    "(id INTEGER not NULL, " +
                    " first_name VARCHAR(255), " +
                    " last_name VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";
            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.executeUpdate();

            String sql2 = "INSERT INTO student VALUES (1, 'apple', 'google', 23)";
            String sql3 = "INSERT INTO student VALUES (2, 'bob', 'douglas', 24)";
            String sql4 = "INSERT INTO student VALUES (3, 'linus', 'rose', 25)";
            String sql5 = "INSERT INTO student VALUES (4, 'newton', 'kokodayo', 26)";
            String sql6 = "INSERT INTO student VALUES (5, 'alice', 'balabala', 27)";

            preparedStatement.executeUpdate(sql2);
            preparedStatement.executeUpdate(sql3);
            preparedStatement.executeUpdate(sql4);
            preparedStatement.executeUpdate(sql5);

            preparedStatement.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
