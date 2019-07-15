package org.wlxy.example.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wlxy.example.common.PageParam;
import org.wlxy.example.model.User;
import org.wlxy.example.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("thymeleaf")
public class ThymeleafController {

    @Autowired
    UserService userService;

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request, @RequestParam(value = "name", defaultValue = "springboot-thymeleaf") String name) {
        request.setAttribute("name", name);
        return "hello";
    }


    @RequestMapping("/showUserList")
    public String hello(HttpServletRequest request) {

        PageParam<User> pageParam=new PageParam<User>();

        pageParam.setPageNum(1);
        pageParam.setPageSize(5);
        pageParam.setOrderParams(new String[]{});

        PageInfo<User> userPageInfo = (PageInfo<User>)userService.getAllUser(pageParam);

        request.setAttribute("userList",userPageInfo.getList());

        return "userList";
    }

}