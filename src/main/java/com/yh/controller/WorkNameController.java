package com.yh.controller;

import com.alibaba.fastjson.JSON;
import com.yh.bean.WorkName;
import com.yh.service.WorkNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: yinhui
 * @Date: 2019/4/24 13:58
 * @Version 1.0
 */
@RestController
@RequestMapping("/workname")
public class WorkNameController {

    @Autowired
    private WorkNameService workNameService;

    private ExecutorService executors = Executors.newFixedThreadPool(10);

    public WorkNameController(){

        // jvm关闭的时候先执行该线程钩子
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                int id = 1;
                WorkName workName = workNameService.getById(id);
                String text = JSON.toJSONString(workName); //序列化
                System.out.println("sdf="+text);
            }
        });
    }

    @RequestMapping("/get/{id}")
    public WorkName getByid(@PathVariable("id")Integer id){

        WorkName workName = workNameService.getById(id);
        String text = JSON.toJSONString(workName); //序列化
        WorkName vo = JSON.parseObject(text, WorkName.class); //反序列化
        return workName;
    }

    @PostMapping("/update")
    public void updateByid(){

        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("chargename","yin2");
        workNameService.updateById(map);
    }

    @PostMapping("/getPhone")
    public void getPhone(@RequestParam Map<String,String> map){

        Map<String,String> map2 = map;
        System.out.println(map2);

    }
}
