/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.yh.service;

import com.yh.bean.User;

import java.util.List;

/**
 * @author yinhui
 * @version UserService, v0.1 2018/9/20 14:27
 */
public interface UserService {

    String getName(int id);
    String getTrueName(String truename);

    /**
     * 自定义输入sql语句执行
     * @return
     */
    String findByAll();

    List<User> getUserList(int pageNum, int pageSize);

    int insertUser(String userName ,String trueName,String mobile);
}
