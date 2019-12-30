///*
// * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
// */
//package com.yh.rocketmq;
//
//import com.yh.base.MQException;
//import com.yh.constants.MQEnum;
//import com.yh.rocketmq.base.MessageContent;
//import com.yh.rocketmq.base.Producer;
//import com.yh.rocketmq.base.ProducerWrapper;
//import org.springframework.stereotype.Service;
//
///**
// * @author yinhui
// * @version AppMqProducer, v0.1 2018/10/31 17:41
// */
//@Service
//public class AppMqProducer extends Producer {
//
//    @Override
//    protected ProducerWrapper getProducerWrapper() {
//        ProducerWrapper producerWrapper = new ProducerWrapper();
//        producerWrapper.setGroup(MQEnum.APP_SEND_TWO_GROUP);
////        producerWrapper.setInstanceName("app_send_yinhui");
//        producerWrapper.setInstanceName(Long.toString(System.currentTimeMillis()));
//        return producerWrapper;
//    }
//
//    @Override
//    public boolean sendMessage(MessageContent messageContent) throws MQException {
//        return super.sendMessage(messageContent);
//    }
//
//}
