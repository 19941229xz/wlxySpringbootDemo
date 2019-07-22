package org.wlxy.example.service;

import org.wlxy.example.common.PageParam;
import org.wlxy.example.model.User;


public interface UserService {

    Object getAllUser(PageParam<User> pageParam);

    boolean removeUserById(int id);

    Object addUser(User user);

    boolean updateUser(User user);

    User getUserById(int id);

    User login(String userName,String password);

    User register(User user);

    User userNameIsReged(String userName);

    User getUserByEmail(String email);
}
