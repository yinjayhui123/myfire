/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.yh.Test;

import com.yh.bean.Man;
import com.yh.bean.Person;
import com.yh.bean.User;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yinhui
 * @version Test, v0.1 2018/11/2 10:46
 */
public class Test {

    public static void main(String[] str) throws Exception {

//        List<User> listUser = new ArrayList<>();
//        while(true){
//            listUser.add(new User());
//        }

//        while (true) {
//                         Enhancer enhancer = new Enhancer();
//                        enhancer.setSuperclass(User.class);
//                       enhancer.setUseCache(false);
//                        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invokeSuper(objects, str));
//                     enhancer.create();
//                  }
//
//        User user2 = new User();
//        System.out.println(user2.toString());
//
////        DecimalFormat df = new DecimalFormat("#,##0.00");
////        df.format(null);
//
        ArrayList<String> strings = new ArrayList<>();
//        int sb = 11 % 2;
//        System.out.println("sb="+sb);
//
//
        strings.add("f");
        strings.add("d");
        System.out.println("list="+strings);
//
//        strings.forEach(s -> {
//            System.out.println(s);
//        });
//
//        Map<String,String> hashMap = new HashMap<>();
//        hashMap.put("1","f");
//        hashMap.put("2","d");
//
//        hashMap.forEach((keys,value) ->{
//
//            System.out.println(keys +","+ value);
//        });
//
//        int newCapacity = 7 + (7 >> 1);
//        System.out.println(newCapacity);
//        writeSerializableObject();
//        readSerializableObject();
    }

    // Serializable：把对象序列化
    public static void writeSerializableObject()  throws Exception  {

        Man man = new Man("哈哈","123");
        Person person = new Person(man,"yinhui",65);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("E:\\out.txt"));
        objectOutputStream.writeObject("string");
        objectOutputStream.writeObject(person);

        objectOutputStream.close();
    }

    // Serializable：反序列化对象
    public static void readSerializableObject()  throws Exception {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("E:\\out.txt"));
        String v = (String) inputStream.readObject();
        Person person = ( Person) inputStream.readObject();

        inputStream.close();
        System.out.println( v+", age: " + person.getAge() + ", man username: " + person.getMan().getUsername());
    }
}
