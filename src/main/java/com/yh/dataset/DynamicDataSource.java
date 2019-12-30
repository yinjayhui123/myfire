package com.yh.dataset;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态获取数据源
 * @Author: yinhui
 * @Date: 2019/4/24 10:38
 * @Version 1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }
}
