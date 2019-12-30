///*
// * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
// */
//package com.yh.rocketmq.base;
//
//import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.springframework.beans.factory.annotation.Value;
//
//import javax.annotation.PostConstruct;
//
///**
// * @author yinhui
// * @version Consumer, v0.1 2018/10/31 18:12
// */
//public abstract class Consumer {
//
//    /**
//     * nameServer地址
//     */
//    @Value("${apache.rocketmq.namesrvAddr}")
//    private String namesrvAddr;
//
//    protected DefaultMQPushConsumer consumer;
//
//    @PostConstruct
//    public void init() throws MQClientException {
//        consumer = new DefaultMQPushConsumer();
//        consumer.setNamesrvAddr(namesrvAddr);
//        consumer.setVipChannelEnabled(false);
//        init(consumer);
//    }
//
//    public abstract void init(DefaultMQPushConsumer consumer) throws MQClientException;
//}
