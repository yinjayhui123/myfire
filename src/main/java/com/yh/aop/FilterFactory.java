package com.yh.aop;

import com.yh.annotation.EnableFilter;
import com.yh.service.ActivityFilter;
import org.reflections.Reflections;
import sun.reflect.Reflection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: yinhui
 * @Date: 2020/1/15 16:28
 * @Version 1.0
 */
public class FilterFactory {

    public static List<ActivityFilter> getFilter(String packages){
        List<ActivityFilter> filterList = new ArrayList<>();
        //通过注解扫描指定的包
        Reflections reflections = new Reflections(packages);
        //如果该包下面有被EnableFilter注解修饰的类，那么将该类的实例加入到列表中，并最终返回
        //获取所有带有 EnableFilter 注解的类
        Set<Class<?>> filters = reflections.getTypesAnnotatedWith(EnableFilter.class);
        for (Class filter : filters){
            try {
                filterList.add((ActivityFilter)filter.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return filterList;
    }
}
