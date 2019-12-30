/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.yh.controller;

import com.yh.annotation.RequestLimit;
import com.yh.annotation.SecureSign;
import com.yh.bean.TestUser;
import com.yh.bean.User;
import com.yh.service.UserService;
import com.yh.util.IpConfiguration;
import com.yh.util.SingletonBean;
import com.yh.util.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author yinhui
 * @version HelloController, v0.1 2018/9/18 16:19
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private SpringContextHolder springContextHolder;

    @Autowired
    private UserService userService;

    @Autowired
    private IpConfiguration ipConfiguration;
//    @Value("${version}")
//    private String version;

//    @Autowired
//    private RocketmqProvider rocketmqProvider;
//
//    @Autowired
//    private RocketmqConsumer rocketmqConsumer;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/index")
    public String index(@RequestBody TestUser testUser){
        TestUser s = testUser;
        logger.debug("我在1");
        logger.info("我在2");
        logger.trace("我在3");
        return "this good jobc!";
    }

    @RequestMapping("/rest")
    public String rest(){
        String str="";
        String value = restTemplate.postForObject("http://127.0.0.1:9093/hyjf-admin/activity/activityRecordList2",str,String.class);
        return value;
    }

    @SecureSign
    @GetMapping("/getName/{id}")
    public String getUser(@PathVariable int id){
//        logger.info("version="+version);
        String name = userService.findByAll();
        return name;
    }

    @GetMapping("/getTrueName/{trueName}")
    public String getTrueName(@PathVariable String trueName){
//        logger.info("version="+version);
        String name = userService.getTrueName(trueName);
        return name;
    }

    @GetMapping("/getUserList/{pageNo}/{pageSize}")
    public List<User> getUserList(@PathVariable int pageNo,@PathVariable int pageSize){

//        logger.info("version="+version);
        List<User> name = userService.getUserList(pageNo,pageSize);
        return name;
    }

    @GetMapping("/insertUser/{userName}/{trueName}/{mobile}")
    public int insertUser(@PathVariable String userName ,@PathVariable String trueName,@PathVariable String mobile){

//        logger.info("version="+version);
        int id = userService.insertUser(userName,null,mobile);
        return id;
    }

    /**
     * 给mq发送消息
     * @return
     */
    @RequestMapping("sendMq")
    public String sendMq(){

//        rocketmqProvider.producer();
        return null;
    }

    /**
     * 接受mq发送消息
     * @return
     */
    @RequestMapping("acceptMq")
    public String acceptMq(){

//        rocketmqConsumer.consumer();
        return null;
    }

    /**
     * 测试单例模式
     * @return
     */
    @GetMapping("/getSingleton/{swich}")
    public String getUser(@PathVariable String swich) throws InterruptedException {
        SingletonBean vo = SingletonBean.INSTANCE;
        System.out.println(vo.hashCode());
        String name = "";
//        String name = vo.getValue(swich);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程1请求单例返回："+vo.getValue("on"));
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程2请求单例返回："+vo.getValue("onss"));
            }
        });
        thread.start();
        Thread.currentThread().sleep(1000);
        thread2.start();

        return name;
    }

    /**
     * 获取项目的ip和端口号
     * @return
     */
    @RequestMapping("/getUrl")
    public String getUrl(){
        return ipConfiguration.getUrl();
    }

    /**
     * 根据不同的平台获得金额
     * @param plat
     * @return
     */
    @RequestMapping("/getMoney/{plat}")
    public String getMoney(@PathVariable String plat){

        // plat = Wechat 、Alipay
        String money = "";
        try {
            money = springContextHolder.getHandlerInstance(plat).getAccountMoney();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return money;
    }

    /**
     * 请求重复验证
     * 获取项目的ip和端口号
     * @return
     */
    @RequestLimit(seconds = 3)
    @RequestMapping("/getUrlReqLimit")
    public String getUrlReqLimit(){
        return ipConfiguration.getUrl();
    }
}
