package org.wlxy.example.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wlxy.example.model.User;
import org.wlxy.example.service.UserService;

import javax.validation.Valid;
import java.util.Random;

@RestController
@RequestMapping("email")
@CrossOrigin
public class EmailController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/sendPassWordToEmail/{email}")
    public Object user(@PathVariable("email") String email) throws Exception {

        User u= userService.getUserByEmail(email);

        if(u!=null){
            if(u.getIsActive()==1){
                String mima = (new Random().nextInt(899999)+100000)+"mm";
                u.setPassword(mima);
                userService.updateUser(u);

                EmailUtil.sendEmail(mima,email);
                return MyRsp.success(null).msg("发送成功请注意查收邮件");
            }else{
                return MyRsp.error().msg("该账号还未激活");
            }
        }else {
            return MyRsp.error().msg("邮箱未注册");
        }


//        return u!=null?MyRsp.success(u).msg("注册成功"):
//                MyRsp.error().msg("注册失败");
//        return u!=null?(u.getIsActive()==0?MyRsp.error().msg("用户未激活"):MyRsp.success(null).msg("发送成功请注意查收邮件"))
//                :(MyRsp.error().msg("邮箱不存在"));
    }






}
