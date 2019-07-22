package org.wlxy.example.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wlxy.example.model.User;
import org.wlxy.example.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("reg")
@CrossOrigin
public class RegController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/user")
    public Object user(@RequestBody@Valid User user){

        User u= userService.register(user);


        return u!=null?MyRsp.success(u).msg("注册成功"):
                MyRsp.error().msg("注册失败");
    }

    @PostMapping(value = "/userNameIsReged/{userName}")
    public Object userNameIsReged(@PathVariable("userName") String userName){

        User u= userService.userNameIsReged(userName);


        return u!=null?MyRsp.error().msg("用户名已经被占用"):
                MyRsp.success(null).msg("可以使用这个用户名");
    }





}
