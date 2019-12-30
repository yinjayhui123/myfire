package com.yh.dataset;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * aop拦截设置本地线程变量，进行数据源切换
 * @Author: yinhui
 * @Date: 2019/4/24 11:36
 * @Version 1.0
 */
@Aspect
@Component
@Order(1)
public class DataSourceAop {

    private final int dataSourceNumber = 0;
    private AtomicInteger count = new AtomicInteger(0);

    /**
     * 数据源控制优先级别(值越小优先级越高)，读方法切面
     */
    public static final String[] DATASOURCE_QUERY_PREFIX = {"select","query","count","search","get","find","check","export"};

    @Pointcut("execution(* com.yh..*Service.*(..))")
    public void serviceAspect(){}

    @Before("serviceAspect()")
    public void switchDataSource(JoinPoint joinPoint){

        //获取包名
//        String packageStr = joinPoint.getTarget().toString();
        //获取方法名
        String packageStr = joinPoint.getSignature().getName();

        Boolean isQuery = isQueryMethod(packageStr);
        if(isQuery){
            // 读简单负载均衡
            DynamicDataSourceContextHolder.useSlaveDataSource();
            return;
        }
        DynamicDataSourceContextHolder.useWrite();
    }

    @After("serviceAspect()")
    public void restoreDataSource(JoinPoint joinPoint){
        DynamicDataSourceContextHolder.clearDataSourceKey();
    }

    @AfterThrowing("serviceAspect()")
    public void restoreDataSourceThrowing(JoinPoint joinPoint){
        DynamicDataSourceContextHolder.clearDataSourceKey();
    }

    private Boolean isQueryMethod(String methodName){
        for(String prefix : DATASOURCE_QUERY_PREFIX){
            if(methodName.startsWith(prefix)){
                return true;
            }
        }
        return false;
    }
}
