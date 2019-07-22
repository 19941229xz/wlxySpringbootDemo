package org.wlxy.example.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wlxy.example.model.User;
import org.wlxy.example.service.UserService;

@RestController
@RequestMapping("login")
@CrossOrigin
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/user")
    public Object user(@RequestBody User user){

        User u= userService.login(user.getUserName(),user.getPassword());


        return u!=null?MyRsp.success(u):
        MyRsp.error().msg("登录失败");
    }




}
