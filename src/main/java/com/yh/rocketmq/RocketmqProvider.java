///*
// * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
// */
//package com.yh.rocketmq;
//
//import org.apache.rocketmq.client.producer.DefaultMQProducer;
//import org.apache.rocketmq.client.producer.MessageQueueSelector;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.common.message.Message;
//import org.apache.rocketmq.common.message.MessageQueue;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StopWatch;
//
//import java.util.List;
//
///**
// * @author yinhui
// * @version RocketmqProvider, v0.1 2018/10/26 11:00
// */
//@Service
//public class RocketmqProvider {
//
//    /**
//     * 生产者的组名
//     */
//    @Value("${apache.rocketmq.producer.producerGroup}")
//    private String producerGroup;
//
//    /**
//     * nameServer地址
//     */
//    @Value("${apache.rocketmq.namesrvAddr}")
//    private String namesrvAddr;
//
//    //@PostContruct是spring框架的注解，在方法上加该注解会在项目启动的时候执行该方法，也可以理解为在spring容器初始化的时候执行该方法。
////    @PostConstruct
//    public void producer(){
//        //生产者的组名
//        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
//        //指定NameServer地址，多个地址以 ; 隔开
//        producer.setNamesrvAddr(namesrvAddr);
//
//        try{
//
//            /**
//             * Producer对象在使用之前必须要调用start初始化，初始化一次即可
//             * 注意：切记不可以在每次发送消息时，都调用start方法
//             */
//            producer.start();
//            producer.setSendMsgTimeout(500000);
//
//            Message message = new Message("TopicTestYh1","push","发送消息----生产者---".getBytes());
//
//            StopWatch stop = new StopWatch();
//            stop.start();
//
////            for (int i=0;i<10;i++){
//                SendResult result = producer.send(message, new MessageQueueSelector() {
//                    @Override
//                    public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
//                        Integer id = (Integer) o;
//                        int index = id % list.size();
//                        return list.get(index);
//                    }
//                },1);
//                System.out.println("发送响应：MsgId:" + result.getMsgId() + "，发送状态:" + result.getSendStatus());
////            }
//            stop.stop();
//            System.out.println("----------------发送十条消息耗时：" + stop.getTotalTimeMillis());
//
////            for (int i = 0; i < 100; i++) {
////                //Create a message instance, specifying topic, tag and message body.
////                Message msg = new Message("TopicTest" /* Topic */,
////                        "TagA" /* Tag */,
////                        ("Hello RocketMQ " +
////                                i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
////                );
////                //Call send message to deliver message to one of brokers.
////                SendResult sendResult = producer.send(msg);
////                System.out.printf("%s%n", sendResult);
////            }
//            //Shut down once the producer instance is not longer in use.
//
//        }catch (Exception e){
//
//            e.printStackTrace();
//        }finally {
//            producer.shutdown();
//        }
//
//    }
//
//}
