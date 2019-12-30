package com.yh.util;

/**
 * @Author: yinhui
 * @Date: 2019/6/28 11:11
 * @Version 1.0
 */
public enum SingletonBean {

    INSTANCE;

    private SingletonBean(){

    }

    public String getValue(String swich){
        if("on".equals(swich)){
            //测试单例模式，多个线程请求同一单例类的同一方法，线程会阻塞
            //证明结果：单例模式不等于单线程不等于同步。单例就像 A a=new A();
            try {
                Thread.currentThread().sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "method is sleeps";
        }else{
            return "method is active";
        }
    }
}
