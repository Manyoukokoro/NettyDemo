package com.nekotori.entity.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户数据，用于模拟数据库，在服务器端初始化
 */


@NoArgsConstructor
@Data
public class UserList {
    private List<UserModel> userList = new ArrayList<UserModel>();

    public void addUser(UserModel user){
        userList.add(user);
    }

    /**
     *
     * @param id
     * @return
     */
    public UserModel findUserById(String id){
        for(UserModel user:userList){
            if(user.getId().equals(id)) return user;
        }
        return null;
    }

    public UserModel findUserByName(String name){
        for(UserModel user:userList){
            if(user.getName().equals(name)) return user;
        }
        return null;
    }

    public void deleteUser(UserModel user){
        userList.remove(user);
    }

}
