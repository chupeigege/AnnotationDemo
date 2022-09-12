package vip.aquan.annotationdemo.entity;

import lombok.Builder;
import lombok.Data;
import vip.aquan.annotationdemo.annotation.Length;
/**
*
/**
 * @author: wcp
 * @date: 2020/3/22 15:13
 * @Description:
 */
@Data
@Builder
public class User extends Validate{

    @Length(min = 6,max = 10,errorMsg = "用户名长度必须是6-10位")
    private String username;
    @Length(min = 6,max = 10,errorMsg = "密码长度必须是6-10位")
    private String password;


}
