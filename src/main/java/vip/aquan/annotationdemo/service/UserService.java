package vip.aquan.annotationdemo.service;

import vip.aquan.annotationdemo.entity.User;

import java.util.List;

/**
 * @author: wcp
 * @date: 2020/3/28 16:34
 * @Description:
 */
public interface UserService {
    List<User> findList();
}
