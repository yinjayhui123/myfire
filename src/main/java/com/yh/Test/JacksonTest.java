package com.yh.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yh.bean.TestUser;
import com.yh.bean.WorkName;
import com.yh.util.ConvertUtile;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Jackson是一个简单基于Java应用库，Jackson可以轻松的将Java对象转换成json对象和xml文档，同样也可以将json、xml转换成Java对象
 * @Author: yinhui
 * @Date: 2019/7/16 11:02
 * @Version 1.0
 */
public class JacksonTest {

    public static void main(String[] str) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        String value = "{\"username\":\"sdf\",\"truename\":\"王五\"}";

        TestUser testUser = new TestUser();
        testUser.setTruename("水电费");
        testUser.setUsername("sdf");

        WorkName workName = new WorkName();
//        workName.setChargeName("历史上");
        testUser.setLocation(workName);
        String s = objectMapper.writeValueAsString(workName);
        System.out.println(s);

        String workValue = "{\"id\":2,\"workName\":null,\"chargeName\":\"历史上\",\"chargeMail\":null,\"status\":1,\"createUser\":null,\"updateUser\":null,\"createTime\":null,\"updateTime\":null}";
//        String workValue ="{\"username\":\"sdf\",\"truename\":\"水电费\",\"mobile\":null,\"totaldebt_detail\":null,\"location\":{\"id\":null,\"workName\":\"23fsdf\",\"chargeName\":\"历史上\",\"chargeMail\":null,\"status\":null,\"createUser\":null,\"updateUser\":null,\"createTime\":null,\"updateTime\":null}}";
//        TestUser testUser2 =objectMapper.readValue(workValue, TestUser.class);

        WorkName user = objectMapper.readValue(workValue, WorkName.class);
        Class cl = user.getClass();
        Field field = cl.getDeclaredField("chargeName");
        field.setAccessible(true);
        Object ob = field.get(user);
        Method[] t = user.getClass().getMethods();
        Class c = t[0].getDeclaringClass();
        String sb = c.getName();
        Method[] t2 = user.getClass().getDeclaredMethods();

        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("workName","谁说的");
        map.put("chargeName","水电费");
        map.put("status",1);
        WorkName workName1 = ConvertUtile.convertMapToObject2(map,WorkName.class);

        //不支持private set方法的copy
        BeanUtils.copyProperties(user,workName);
        System.out.println("sdf");
    }
}
