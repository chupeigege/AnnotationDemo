package vip.aquan.annotationdemo.annotation;

import java.lang.annotation.*;

/**
 * @author: wcp
 * @date: 2020/3/22 15:35
 * @Description: 日志注解类
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /** 要执行的功能 比如：当前所有故障 **/
    String operationFunction() default "";

    /** 要执行的功能所属模块 比如：故障管理 **/
    String operationModule() default "";
}
