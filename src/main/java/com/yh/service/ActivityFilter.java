package com.yh.service;

import com.yh.bean.ActivityBean;
import com.yh.util.FilterChain;

/**
 * 活动过滤器
 * @Author: yinhui
 * @Date: 2020/1/15 14:44
 * @Version 1.0
 */
public interface ActivityFilter {

    void execute(ActivityBean activityBean, FilterChain chain);
}
