package com.yh.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.stereotype.Component;

/**
 * spring工具类 方便在非spring管理环境中获取bean
 * @Author: yinhui
 * @Date: 2019/8/2 17:38
 * @Version 1.0
 */
@Component
public final class SpringUtile implements BeanFactoryPostProcessor {

    /** Spring应用上下文环境 */
    private static ConfigurableListableBeanFactory configurableListableBeanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        String[] strings = beanFactory.getBeanDefinitionNames();
//        BeanDefinitionRegistry bor= (BeanDefinitionRegistry) beanFactory;
        SpringUtile.configurableListableBeanFactory = beanFactory;
    }

    public static <T> T getBean(String name){
        T ob = (T) configurableListableBeanFactory.getBean(name);
        return ob;
    }
}
