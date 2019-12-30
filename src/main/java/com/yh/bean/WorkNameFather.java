package com.yh.bean;

import java.util.Date;

/**
 * @Author: yinhui
 * @Date: 2019/7/17 15:49
 * @Version 1.0
 */
public class WorkNameFather {

    /**
     * 1可用，2禁用
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * 创建操作人
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * 更新操作人
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date updateTime;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
