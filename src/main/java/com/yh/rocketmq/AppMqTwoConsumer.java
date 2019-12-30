///*
// * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
// */
//package com.yh.rocketmq;
//
//import com.yh.constants.MQEnum;
//import com.yh.rocketmq.base.Consumer;
//import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
//import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @author yinhui
// * @version AppMqConsumer, v0.1 2018/10/31 18:28
// */
//@Service
//public class AppMqTwoConsumer extends Consumer {
//
//    private static final Logger logger = LoggerFactory.getLogger(AppMqTwoConsumer.class);
//
//    @Override
//    public void init(DefaultMQPushConsumer consumer) throws MQClientException {
//        consumer.setConsumerGroup(MQEnum.APP_SEND_GROUP.name());
////        consumer.setInstanceName(MQEnum.APP_SEND_KEYS.name());
//        consumer.setInstanceName(Long.toString(System.currentTimeMillis()));
//        //订阅PushTopic下Tag为push的消息
//        consumer.subscribe(MQEnum.APP_SEND_TOPIC.name(),"*");
//        //从哪里开始消费ConsumeFromWhere
//        //CONSUME_FROM_LAST_OFFSET 会忽略历史消息，消费这之后的消息。CONSUME_FROM_FIRST_OFFSET 会消费所有在broker中的消息
////        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
//        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
//        //消息模式,集群clustering(集群只发一个，广播会发所有的消费者)
//        consumer.setMessageModel(MessageModel.CLUSTERING);
//        consumer.setMessageListener(new MqMessageListener());
//        consumer.start();
//
//    }
//
//    //并发Concurrently,并发消费消息(性能高)。还可以使用顺序监听 Orderly
//    public class MqMessageListener implements MessageListenerConcurrently{
//
//        @Override
//        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//            try{
//                for (MessageExt messageExt : msgs){
//                    //输出消息内容
//                    System.out.println("messageExt: "+messageExt);
//
//                    String messageBody = new String( messageExt.getBody());
//                    System.out.println("456消费响应:msgId: "+messageExt.getMsgId()+", msgBody:"+messageBody);
//                }
//            }catch (Exception e){
//                logger.error("Unable to process message, due to:", e);
//                return ConsumeConcurrentlyStatus.RECONSUME_LATER;//稍后再试
//            }
//            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;//消费成功
//        }
//    }
//}
