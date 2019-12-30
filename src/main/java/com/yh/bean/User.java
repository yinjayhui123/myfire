/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.yh.bean;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yinhui
 * @version User, v0.1 2018/9/20 14:18
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不为空")
    private int id;
    private String username;
    private String truename;
    private String mobile;
    private Date ctm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Date getCtm() {
        return ctm;
    }

    public void setCtm(Date ctm) {
        this.ctm = ctm;
    }
}
