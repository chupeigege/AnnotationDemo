package vip.aquan.annotationdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.aquan.annotationdemo.annotation.Log;
import vip.aquan.annotationdemo.entity.Permission;
import vip.aquan.annotationdemo.entity.User;
import vip.aquan.annotationdemo.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: wcp2
 * @date: 2020/3/22 15:37
 * @Description: 用户控制器
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @Log(operationModule = "用户管理", operationFunction = "登陆")
    public String login(User user){
        return user.getUsername() + "---登录成功---";
    }

    @RequestMapping("/findList")
    @Log(operationModule = "用户管理", operationFunction = "查询用户列表")
    public Object findList(Permission permission){
        //调用此方法,需要权限  permission = view 或者 edit
        return userService.findList();
    }
}
