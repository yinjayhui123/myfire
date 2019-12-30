/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.yh.rocketmq.base;

import com.yh.constants.MQEnum;

import java.io.Serializable;

/**
 * @author yinhui
 * @version MessageContent, v0.1 2018/10/31 16:37
 */
public class MessageContent implements Serializable {

    private static final long serialVersionUID = 8445773977080406428L;

    //topic
    public final String topic;
    public final String tags;
    //每个消息在业务局面的唯一标识码通过 topic，key 来查询返条消息内容，以及消息被谁消费,查询的时候 非常重要
    public final String keys;
    public final Object body;

    public MessageContent(String topic,String tags,String keys,Object body){

        this.topic = topic;
        this.tags = tags;
        this.keys = keys;
        this.body = body;
    }

    public MessageContent(Enum topic,Enum keys,Object body){
        this(topic.name(), MQEnum.DEFAULT_TAGS.name(), keys.name(), body);
    }

}
