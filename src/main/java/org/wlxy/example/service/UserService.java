package org.wlxy.example.service;

import org.wlxy.example.common.PageParam;
import org.wlxy.example.model.User;

import java.util.List;

public interface UserService {

    Object getAllUser(PageParam<User> pageParam);

    boolean removeUserById(int id);

    Object addUser(User user);

    boolean updateUser(User user);

    User getUserById(int id);
}
