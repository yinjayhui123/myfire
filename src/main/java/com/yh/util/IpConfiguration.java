package com.yh.util;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取项目的ip和端口号
 * @Author: yinhui
 * @Date: 2019/8/27 18:02
 * @Version 1.0
 */
@Component
public class IpConfiguration implements ApplicationListener<WebServerInitializedEvent> {

    private int serverPost;

    /**
     * 获取端口
     * @param event
     */
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPost = event.getWebServer().getPort();
    }

    public String getUrl(){
        InetAddress address = null;
        try {
            //获取地址
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "http://"+address.getHostAddress()+":"+serverPost;
    }
}
