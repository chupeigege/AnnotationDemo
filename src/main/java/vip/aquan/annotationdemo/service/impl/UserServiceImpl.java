package vip.aquan.annotationdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.aquan.annotationdemo.dao.UserMapper;
import vip.aquan.annotationdemo.entity.User;
import vip.aquan.annotationdemo.service.UserService;

import java.util.List;

/**
 * @author: wcp
 * @date: 2020/3/28 16:41
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> findList() {
        return userMapper.findList();
    }
}
