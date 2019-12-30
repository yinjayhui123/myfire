///*
// * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
// */
//package com.yh.rocketmq;
//
//import com.yh.constants.MQEnum;
//import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
//import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
///**
// * @author yinhui
// * @version RocketmqConsumer, v0.1 2018/10/26 13:39
// */
//@Service
//public class RocketmqConsumer {
//
//    /**
//     * 消费者的组合
//     */
//    @Value("${apache.rocketmq.consumer.PushConsumer}")
//    private String consumerGroup;
//
//    /**
//     * nameServer地址
//     */
//    @Value("${apache.rocketmq.namesrvAddr}")
//    private String namesrvAddr;
//
////    @PostConstruct
//    public void consumer(){
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(MQEnum.APP_SEND_GROUP.name());
//        consumer.setNamesrvAddr(namesrvAddr);
//
//        try{
//
//            //订阅PushTopic下Tag为push的消息
//            consumer.subscribe("TopicTestYh1","*");
////            consumer.subscribe(MQEnum.APP_SEND_TOPIC.name(),"*");
////            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
////            consumer.setMessageModel(MessageModel.BROADCASTING);
////            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
//            consumer.registerMessageListener((MessageListenerConcurrently) (list, context) -> {
//                try{
//                    for (MessageExt messageExt : list){
//                        //输出消息内容
//                        System.out.println("messageExt: "+messageExt);
//
//                        String messageBody = new String( messageExt.getBody());
//                        System.out.println("消费响应:msgId: "+messageExt.getMsgId()+", msgBody:"+messageBody);
//                    }
//                }catch (Exception e){
//                    e.printStackTrace();
//                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
////                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;//稍后再试
//                }
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
////                return ConsumeOrderlyStatus.SUCCESS;//消费成功
//            });
//            consumer.start();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//}
