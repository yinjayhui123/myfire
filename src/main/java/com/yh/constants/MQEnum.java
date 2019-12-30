/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.yh.constants;

/**
 * @author yinhui
 * @version MQEnum, v0.1 2018/10/31 17:28
 */
public enum MQEnum {

    /** 不特别指定的时候使用的默认tag*/
    DEFAULT_TAGS("DEFAULT_TAGS"),

    APP_SEND_GROUP("APP_SEND_GROUP"),
    APP_SEND_TOPIC("APP_SEND_TOPIC"),
    APP_SEND_KEYS("APP_SEND_KEYS"),

    APP_SEND_YIN_GROUP("APP_SEND_YIN_GROUP"),
    APP_SEND_TOPIC_YIN("APP_SEND_TOPIC_YIN");

    private String value;

    private MQEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
