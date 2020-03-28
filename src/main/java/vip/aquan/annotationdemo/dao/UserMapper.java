package vip.aquan.annotationdemo.dao;

import org.springframework.stereotype.Repository;
import vip.aquan.annotationdemo.entity.User;

import java.util.List;

/**
 * @author: wcp
 * @date: 2020/3/28 16:34
 * @Description:
 */
@Repository
public interface UserMapper {
    List<User> findList();
}
