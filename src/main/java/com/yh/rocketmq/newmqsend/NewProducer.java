package com.yh.rocketmq.newmqsend;

import com.yh.rocketmq.base.MessageContent;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 通用发消息
 * @Author: yinhui
 * @Date: 2018/12/18 10:42
 * @Version 1.0
 */
@Component
public class NewProducer  {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送普通队列通用方法
     * @param messageContent
     * @return
     */
    public boolean messageSend(MessageContent messageContent){
        // Send string
        SendResult sendResult = null;
        if(StringUtils.isNotEmpty(messageContent.keys)){
            Message<?> msg = MessageBuilder.withPayload(messageContent.body).
                    setHeader(RocketMQHeaders.KEYS, messageContent.keys).build();
            sendResult = rocketMQTemplate.syncSend(messageContent.topic + ":" + messageContent.tags, msg);
            System.out.printf("syncSend1 to topic %s sendResult=%s %n", messageContent.topic, sendResult);
        }else{

            // Send string with spring Message
            sendResult = rocketMQTemplate.syncSend(messageContent.topic + ":" + messageContent.tags, messageContent.body);
            System.out.printf("syncSend2 to topic %s sendResult=%s %n", messageContent.topic, sendResult);
        }

        if(sendResult != null && sendResult.getSendStatus() == SendStatus.SEND_OK){
            return true;
        }else{
            return false;
        }

    }

}
