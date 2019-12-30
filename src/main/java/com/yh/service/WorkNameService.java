package com.yh.service;

import com.yh.bean.WorkName;

import java.util.Map;

/**
 * @Author: yinhui
 * @Date: 2019/4/24 13:52
 * @Version 1.0
 */
public interface WorkNameService {

    WorkName getById(Integer id);

    void updateById(Map<String,Object> map);
}
