package com.nekotori.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 用户数据，用于模拟数据库，在服务器端初始化
 */


@NoArgsConstructor
@Data
public class UserData {
    private List<User> userList;

    public void addUser(User user){
        userList.add(user);
    }

    /**
     *
     * @param id
     * @return
     */
    public User findUserById(String id){
        for(User user:userList){
            if(user.getId().equals(id)) return user;
        }
        return null;
    }

    public User findUserByName(String name){
        for(User user:userList){
            if(user.getName().equals(name)) return user;
        }
        return null;
    }

    public void deleteUser(User user){
        userList.remove(user);
    }

}
