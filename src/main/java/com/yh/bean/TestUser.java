package com.yh.bean;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yinhui
 * @Date: 2019/7/16 9:28
 * @Version 1.0
 */
public class TestUser {

    private String username;
    private String truename;
    private String mobile;
    private Map totaldebt_detail;
    private WorkName location;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Map getTotaldebt_detail() {
        return totaldebt_detail;
    }

    public void setTotaldebt_detail(Map totaldebt_detail) {
        this.totaldebt_detail = totaldebt_detail;
    }

    public WorkName getLocation() {
        return location;
    }

    public void setLocation(WorkName location) {
        this.location = location;
    }
}

