/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.yh.controller;

import com.yh.bean.User;
import com.yh.constants.MQEnum;
import com.yh.rocketmq.base.MessageContent;
import com.yh.rocketmq.newmqsend.NewProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * @author yinhui
 * @version AppMqController, v0.1 2018/10/31 17:54
 */
@RestController
@RequestMapping("/app")
public class AppMqController {

    private static final Logger logger = LoggerFactory.getLogger(AppMqController.class);

    @Autowired
    private NewProducer newProducer;
//    private AppMqProducer appMqProducer;

    /**
     * 给mq发送消息
     * @return
     */
    @RequestMapping("/sendMq")
    public String sendMq(){
        try {
            ArrayList<String> strings = new ArrayList<>();
            strings.add("f");
            StopWatch stop = new StopWatch();
            stop.start();
            User user = new User();
            user.setUsername("我是yinhui");
            newProducer.messageSend(new MessageContent(MQEnum.APP_SEND_TOPIC_YIN,MQEnum.APP_SEND_KEYS,user));
//            for(int i=0;i<10;i++){
//                new Thread(""+i){
//                    @Override
//                    public  void run(){
//                        try {
//                            appMqProducer.sendMessage(new MessageContent(MQEnum.APP_SEND_TOPIC,MQEnum.APP_SEND_KEYS,("我是yinhui"+getName()).getBytes()));
//                        } catch (MQException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }.start();
//
//                new Thread(""+i){
//                    @Override
//                    public  void run(){
//                        try {
//                            appMqProducer.sendMessage(new MessageContent(MQEnum.APP_SEND_TOPIC,MQEnum.APP_SEND_KEYS,("你是yinhui"+getName()).getBytes()));
//                        } catch (MQException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }.start();
//
////                try {
////                    Thread.sleep(1000);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
//            }
            stop.stop();
            logger.info("------发送10条耗时:"+stop.getTotalTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/sendMqs")
    public String gets(@Valid @RequestBody User ss){

        return "";

    }
}
