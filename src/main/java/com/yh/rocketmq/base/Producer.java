///*
// * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
// */
//package com.yh.rocketmq.base;
//
//import com.yh.base.MQException;
//import org.apache.rocketmq.client.exception.MQBrokerException;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.client.producer.DefaultMQProducer;
//import org.apache.rocketmq.client.producer.MessageQueueSelector;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.client.producer.SendStatus;
//import org.apache.rocketmq.common.message.Message;
//import org.apache.rocketmq.common.message.MessageQueue;
//import org.apache.rocketmq.remoting.exception.RemotingException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.util.Assert;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//
///**
// * 生产者
// * @author yinhui
// * @version Producer, v0.1 2018/10/31 14:49
// */
//public abstract class Producer {
//
//    public static int s = 0;
//
//    /**
//     * nameServer地址
//     */
//    @Value("${apache.rocketmq.namesrvAddr}")
//    private String namesrvAddr;
//
//    private DefaultMQProducer producer;
//
//    //@PostContruct是spring框架的注解，在方法上加该注解会在项目启动的时候执行该方法，也可以理解为在spring容器初始化的时候执行该方法。
//    @PostConstruct
//    protected void init() throws MQClientException {
//        ProducerWrapper wrapper = this.getProducerWrapper();
//        Assert.notNull(wrapper,"ProducerWrapper must not be null");
//
//        //生产者的组名
//        producer = new DefaultMQProducer(wrapper.getGroup());
//        //指定NameServer地址，多个地址以 ; 隔开
//        producer.setNamesrvAddr(namesrvAddr);
//        //设置实例名称
//        producer.setInstanceName(wrapper.getInstanceName());
//        //是否启用vip netty通道以发送消息,默认为true
//        //mq默认长连接为 netty
//        producer.setVipChannelEnabled(false);
//        //开启生产者
//        producer.start();
//    }
//
//    protected abstract ProducerWrapper getProducerWrapper();
//
//    protected boolean send(Message message) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
////        SendResult sendResult = producer.send(message);
//        String value = new String( message.getBody());
//        int i = 0;
//        if(value.contains("你是")){
//            i = 1;
//        }
//        s++;
          //2、发送消息是 针对每条消息选择对应的队列
//        SendResult sendResult = producer.send(message, new MessageQueueSelector() {
//            @Override
//            public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
//                Integer id = (Integer) o;
                  //6、保证同一个订单号 一定分配在同一个queue上
//                int index = id % list.size();
//                return list.get(index);
//            }
//        },s);
//
//        if(sendResult != null && sendResult.getSendStatus().equals(SendStatus.SEND_OK)){
//            return true;
//        }
//
//        return false;
//    }
//
//    protected boolean sendMessage(MessageContent messageContent) throws MQException {
//        try{
//            Message message = new Message(messageContent.topic,messageContent.tags,messageContent.keys,messageContent.body);
////            message.setDelayTimeLevel(3); //定时发送
//            boolean result = this.send(message);
//            return result;
//        }catch (RemotingException  | MQClientException | MQBrokerException e){
//            throw new MQException("mq send error",e);
//        }catch (InterruptedException e){
//            //这里因为线程已经在运行了,所以可以设置中断状态,
//            Thread.currentThread().interrupt();
//            throw new MQException("mq send error",e);
//        }
//    }
//}
