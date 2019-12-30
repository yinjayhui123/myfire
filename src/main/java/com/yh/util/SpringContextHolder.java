package com.yh.util;

import com.yh.annotation.PlatHandler;
import com.yh.service.AbstractAccountHandler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * spring工具类 方便在非spring管理环境中获取bean
 * @Author: yinhui
 * @Date: 2019/8/2 15:04
 * @Version 1.0
 */
@Component
public final class SpringContextHolder implements ApplicationContextAware {

    /** Spring应用上下文环境 */
    private static ApplicationContext applicationContext;

    /** key为platNum，value为class*/
    public final static Map<String,Class> handlerMap = new HashMap<>();

    /** 防止被实例化 */
    private SpringContextHolder(){}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static <T> T getBean(String name){
        return  (T)applicationContext.getBean(name);
    }

    @PostConstruct
    public void init() throws Exception{
        Map<String,Object> beans =  applicationContext.getBeansWithAnnotation(PlatHandler.class);
        // 遍历带有PlatHandler注释的类
        if(beans != null && beans.size() > 0){
            for(Object serviceBean : beans.values()){
                String name = serviceBean.getClass().getAnnotation(PlatHandler.class).value();
                handlerMap.put(name,serviceBean.getClass());
            }
        }
    }

    public AbstractAccountHandler getHandlerInstance(String plat) throws Exception {

        Class target = handlerMap.get(plat);
        if(target == null){
            throw new RuntimeException("不支持该平台");
        }
        AbstractAccountHandler hander = (AbstractAccountHandler)applicationContext.getBean(target);
        return hander;

    }
}
