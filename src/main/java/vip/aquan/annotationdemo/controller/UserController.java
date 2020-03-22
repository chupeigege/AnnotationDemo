package vip.aquan.annotationdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.aquan.annotationdemo.annotation.Log;
import vip.aquan.annotationdemo.entity.User;

/**
 * @author: wcp2
 * @date: 2020/3/22 15:37
 * @Description: 用户控制器
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login")
    @Log(operationModule = "用户管理", operationFunction = "登陆")
    public String login(User user){
        return user.getUsername() + "---登录成功---";
    }
}
