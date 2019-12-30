package com.yh.service;

/**
 * @Author: yinhui
 * @Date: 2019/11/18 11:02
 * @Version 1.0
 */
public abstract class AbstractAccountHandler {

    /**
     * 不同平台获得用户金额
     * @return
     */
    abstract public String getAccountMoney();
}
