package vip.aquan.annotationdemo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import vip.aquan.annotationdemo.entity.Validate;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Method;

/**
 * @author: wcp
 * @date: 2020/3/22 15:30
 * @Description: 参数切面，应用于入参时，检验参数规范
 */
@Aspect
@Component
public class ArgsAspect {

    @Pointcut("execution(public * vip.aquan.annotationdemo.controller..*.*(..)) ")
    public void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        //1.继承同一个类，控制获取的对象以及属性
        for (Object arg : args) {
            if(arg instanceof Validate){
                String errorMsg = Validate.validate(arg);
                if(null!=errorMsg){
                    throw new RuntimeException(errorMsg);
                }
            }

            //模拟用户的权限
            String[] permissions = new String[]{"view","edit"};
            //mybatis的反射工具
            MetaObject metaObject = SystemMetaObject.forObject(arg);
            boolean flag = false;
            if(metaObject.hasGetter("permission")) {
                String value = (String) metaObject.getValue("permission");
                for (String permission : permissions) {
                    if (permission.equals(value)){
                        flag = true;
                        break;
                    }
                }
            }
            if(!flag){
                throw new RuntimeException("访问失败,没有权限!");
            }
        }


        return point.proceed();
    }

}
