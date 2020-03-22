package vip.aquan.annotationdemo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vip.aquan.annotationdemo.annotation.Log;
import vip.aquan.annotationdemo.entity.Validate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: wcp
 * @date: 2020/3/22 15:30
 * @Description: 日志切面
 */
@Order(1)
@Aspect
@Component
public class LogAspect {

    private  static  final Logger logger = LoggerFactory.getLogger(LogAspect. class);

    /**
     *  定义Controller层切点( system包下的方法、带@Log注解的方法)
     *  源码的bug，必须要用联合 execution && @annotation 的方式，否则报异常时会重复进入doBefore
     */
    @Pointcut("execution(public * vip.aquan.annotationdemo.controller..*.*(..)) "
            + "&& @annotation(vip.aquan.annotationdemo.annotation.Log)"
    )
    public void webLog() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     * @param joinPoint 切点
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取Request、Response的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String module = "";
        String function = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
//                Class[] clazzs = method.getParameterTypes();
//                if (clazzs.length == arguments.length) {
                    module = method.getAnnotation(Log.class).operationModule();
                    function = method.getAnnotation(Log.class).operationFunction();
                    break;
//                }
            }
        }

        StringBuilder bf = new StringBuilder();
        bf.append(" time:").append(new SimpleDateFormat().format(new Date()))
                .append(" Class:").append(targetName)
                .append(" Method:").append(methodName)
                .append(" Module:").append(module)
                .append(" Function:").append(function);

        logger.info(bf.toString());

    }

}
