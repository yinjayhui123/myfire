/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.yh.base;

/**
 * @author yinhui
 * @version MQException, v0.1 2018/10/31 16:55
 */
public class MQException extends Exception {

    public MQException(){
        super();
    }

    public MQException(String message,Throwable e){
        super(message, e);
    }

}
