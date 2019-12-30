/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.yh.bean;

import java.io.Serializable;

/**
 * @author yinhui
 * @version Man, v0.1 2018/11/2 11:01
 */
public class Man implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    public Man(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
