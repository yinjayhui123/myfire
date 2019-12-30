/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.yh.dao;

import com.yh.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yinhui
 * @version UserMapper, v0.1 2018/9/20 14:14
 */
public interface UserMapper {

//    @Select("SELECT * FROM tb_user WHERE id = #{id}")
    User getById(int i);

    User findByColumn(@Param("column") String column ,@Param("value") String value);

    //自定义输入sql语句执行
    User findByAll(String value);

    List<User> getUser(@Param("age") int age);

    int insertUser(User user);

}
