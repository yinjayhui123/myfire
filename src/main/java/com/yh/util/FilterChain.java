package com.yh.util;

import com.yh.bean.ActivityBean;
import com.yh.service.ActivityFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动链路器
 * 责任链设计模式 实例
 * @Author: yinhui
 * @Date: 2020/1/15 14:49
 * @Version 1.0
 */
public class FilterChain {

    //规则过滤器列表，实现Filter接口的过滤器将真正执行对事件的处理
    private List<ActivityFilter> filterList = new ArrayList<>();

    //过滤器列表的索引
    private int index = 0;

    //向责任链中加入过滤器（单个）
    public void addFilter(ActivityFilter filter){
        filterList.add(filter);
    }

    //向责任链中加入过滤器（多个）
    public void addFilters(List<ActivityFilter> filter){
        filterList.addAll(filter);
    }

    //处理事件（activityBean）从FilterChain中获取过滤器，进行处理，处理完成之后过滤器会再调用该方法，
    //继续执行下一个filter.这就需要在每个Filter接口的实现类中最后一句需要回调FilterChain的doFilter方法。
    public void doFilter(ActivityBean activityBean,FilterChain chain){

        if(index == filterList.size()){
            return;
        }

        ActivityFilter filter = filterList.get(index);
        index++;
        filter.execute(activityBean,chain);
    }

}
