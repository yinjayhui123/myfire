package com.yh.rocketmq;

import com.alibaba.fastjson.JSONObject;
import com.yh.bean.User;
import com.yh.constants.MQEnum;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: yinhui
 * @Date: 2019/3/1 16:22
 * @Version 1.0
 */
@Service
@RocketMQMessageListener(topic = "APP_SEND_TOPIC_YIN", consumerGroup = "APP_SEND_YIN_GROUP")
public class NewAppMqConsumer implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {

    @Override
    public void onMessage(MessageExt message) {
        String value = new String(message.getBody());
        Map<String,String> map = message.getProperties();
        User user = JSONObject.parseObject(message.getBody(), User.class) ;
        System.out.println("fsdf");
//        String z = null;
//        z.length();
    }

    @Override
    public void prepareStart(final DefaultMQPushConsumer consumer) {
        // set consumer consume message from now
        // 如果非第一次启动，那么按照上次消费的位置继续消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        // 设置为集群消费(区别于广播消费)
        consumer.setMessageModel(MessageModel.CLUSTERING);
        //设置最大重试次数
        consumer.setMaxReconsumeTimes(3);
    }
}
