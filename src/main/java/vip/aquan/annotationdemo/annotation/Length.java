package vip.aquan.annotationdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: wcp
 * @date: 2020/3/22 15:22
 * @Description:
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Length {

    int min();//允许字符串传入的最小长度

    int max();//允许字符串传入的最大长度

    String errorMsg();//错误提示信息
}
