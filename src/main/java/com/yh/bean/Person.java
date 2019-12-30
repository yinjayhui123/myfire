/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.yh.bean;

import java.io.Serializable;

/**
 * @author yinhui
 * @version Person, v0.1 2018/11/2 11:02
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private Man man;
    private String username;
    private transient int age;

    public Person() {
        System.out.println("person constru");
    }

    public Person(Man man, String username, int age) {
        this.man = man;
        this.username = username;
        this.age = age;
    }

    public Man getMan() {
        return man;
    }
    public void setMan(Man man) {
        this.man = man;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
