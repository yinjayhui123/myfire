/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.yh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yh.annotation.PlatHandler;
import com.yh.bean.User;
import com.yh.dao.UserMapper;
import com.yh.service.AbstractAccountHandler;
import com.yh.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.tomcat.util.digester.Digester;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yinhui
 * @version UserServiceImpl, v0.1 2018/9/20 14:28
 */
@Service
@PlatHandler(value = "Wechat")
public class UserServiceImpl extends AbstractAccountHandler implements UserService {

    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public String getName(int id){
//        User user = userMapper.getById(id);
//        return user.getUsername();
        User user =userMapper.findByColumn("id",String.valueOf(id));
        return user.getUsername();
    }

    @Async("taskExecutor")
    public String getTrueName(String truename){
        User user = userMapper.findByColumn("truename",truename);
        return "123";
    }

    /**
     * 自定义输入sql语句执行
     * @return
     */
    public String findByAll(){
        User user = userMapper.findByAll("SELECT t1.id, t1.username, t1.truename, t1.mobile FROM ht_admin t1 where id =3");

//        sqlSession.insert("INSERT INTO `ht_admin` ( `username`, `truename` `mobile`) VALUES ('liulifei2', '刘丽飞2', '15821595549')");
//        sqlSession.commit();
        return user.getUsername();
    }

    /**
     *
     * @param pageNum 要显示第几页内容
     * @param pageSize 一页显示多少条
     * @return
     */
    public List<User> getUserList(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<User> user = userMapper.getUser(5);
        PageInfo<User> pageInfo = new PageInfo<>(user);
        Long user2 = pageInfo.getTotal();
        return  pageInfo.getList();
    }

    public int insertUser(String userName ,String trueName,String mobile){
        User user = new User();
        user.setUsername(userName);
        user.setTruename(trueName);
        user.setMobile(mobile);
        userMapper.insertUser(user);
        System.out.println("保存ID为="+user.getId());
        return user.getId();
    }

    public void ss(){
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    }

    @Override
    public String getAccountMoney() {
        return "20";
    }

}
