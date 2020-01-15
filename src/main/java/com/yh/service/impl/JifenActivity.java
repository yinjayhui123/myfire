package com.yh.service.impl;

import com.yh.annotation.EnableFilter;
import com.yh.bean.ActivityBean;
import com.yh.service.ActivityFilter;
import com.yh.util.FilterChain;

/**
 * 积分活动
 * @Author: yinhui
 * @Date: 2020/1/15 15:18
 * @Version 1.0
 */
@EnableFilter
public class JifenActivity implements ActivityFilter {

    @Override
    public void execute(ActivityBean activityBean, FilterChain chain) {

        if(activityBean.getTimeEnd() > System.currentTimeMillis()){

            System.out.println("积分活动："+activityBean.getActivityName()+"进行中~");
        }

        //注意回调FilterChain的doFilter方法，让FilterChain继续执行下一个Filter
        chain.doFilter(activityBean,chain);
    }
}
