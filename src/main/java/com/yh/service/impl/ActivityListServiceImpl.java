package com.yh.service.impl;

import com.yh.aop.FilterFactory;
import com.yh.bean.ActivityBean;
import com.yh.service.ActivityListService;
import com.yh.util.FilterChain;
import org.springframework.stereotype.Component;

/**
 * @Author: yinhui
 * @Date: 2020/1/15 15:52
 * @Version 1.0
 */
@Component
public class ActivityListServiceImpl implements ActivityListService {

    public final static String PACKAGES = "com.yh.service.impl";

    @Override
    public void assginActivity(ActivityBean bean) {

        FilterChain chain = new FilterChain();
//        chain.addFilter(new JifenActivity());
//        chain.addFilter(new Double11Activity());

        //通过 注解的配合使用，不需要一个个添加活动到责任链中
        chain.addFilters(FilterFactory.getFilter(PACKAGES));

        chain.doFilter(bean,chain);
    }
}
