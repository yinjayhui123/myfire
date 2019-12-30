/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.yh.aop;

import com.yh.annotation.RequestTypeEnum;
import com.yh.annotation.SecureSign;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 安全签名切面
 * @author yinhui
 * @version SecureSignAspect, v0.1 2018/10/19 10:57
 */
@Aspect
@Component
public class SecureSignAspect {

    /*
     * execution(* org.com.order.controller..*.*(..))&&@annotation(log)
     * public * *(..) 任意公共方法的执行
     * 表示切点关联表达式，位于org.com.order.controller包下的 并且添加自定义注解 的方法运行
     * @Aspect 表示切面类
     */

    @Around("execution(public * * (..)) && @annotation(com.yh.annotation.SecureSign) ")
    public Object before(ProceedingJoinPoint point){

        Object result = null;

        try{
            MethodSignature methodSignature = (MethodSignature) point.getSignature();
            Method method = methodSignature.getMethod();
            SecureSign secureSign = method.getAnnotation(SecureSign.class);
            if(!secureSign.required()){
                return point.proceed();
            }

            Object[] params = point.getArgs();

            Class<?> classTage = point.getTarget().getClass();
            String simpleName = classTage.getSimpleName();


            Method[] methods = classTage.getMethods();

            boolean flag = (secureSign.type() == RequestTypeEnum.INTERFACE);
            if(flag){

            }else{

            }

            result = point.proceed();
        }catch (Throwable e){

        }

        return result;
    }
}
