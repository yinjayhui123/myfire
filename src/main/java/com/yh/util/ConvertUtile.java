package com.yh.util;

import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 转换工具类
 * @Author: yinhui
 * @Date: 2019/7/17 11:20
 * @Version 1.0
 */
public class ConvertUtile {

    <T> void sets(T t){
    }

    /**
     * 将MAP参数转换成实体类
     * 不支持private修饰的set方法 ,支持有继承父类的字段
     * @param map
     * @param t
     * @return
     * @throws Exception
     */
    public static <T> T convertMapToObject(Map<String,Object> map,Class<T> t) throws Exception {
        Object object = t.newInstance();
        //getDeclaredFields 只能获得当前类的字段，不能获取父类的字段。BeanUtils.getPropertyDescriptors能获取父类的字段
//        Field[] fields = t.getDeclaredFields();

//        BeanInfo beanInfo = Introspector.getBeanInfo(t);
//        PropertyDescriptor[] p2 = beanInfo.getPropertyDescriptors();

        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(t);
        for (PropertyDescriptor descriptor : propertyDescriptors){
            String n = descriptor.getName();
            if(map.get(descriptor.getName()) != null ){

                //直接获取PropertyDescriptor,当set方法为private时会抛出异常。改为通过BeanUtils获得实例对象
 //                PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(),t);

                Method writeMethod = descriptor.getWriteMethod();
                if(writeMethod == null){
                    continue;
                }

                if(!Modifier.isPublic(writeMethod.getModifiers())){
                    writeMethod.setAccessible(true);
                }

                writeMethod.invoke(object,map.get(n));
            }
        }
        return (T)object;
    }

    /**
     * 将MAP参数转换成实体类
     * 直接 T.getMethod 当没有Method时会抛出异常。例如类里面有 serialVersionUID 时，或者set方法有private修饰时
     * @param mapParam  map参数，key值要和实体类的属性一致
     * @param T  转换后的class类
     * @return  转换后的带内容的实体类
     */
    public static <S>S convertMapToObject2(Map<String, Object> mapParam, Class<S> T){
        Object obj = null;
        try {
            //add by cwyang 增加对父类的支持
            obj = T.newInstance();
            List<Object> fileList = getAllFiledList(T);
            for(int i = 0 ; i < fileList.size(); i++){
                Field f = (Field) fileList.get(i);
                String na = f.getName();

                //Field getName不需要设置Accessible ， 只有get()字段值才需要
                f.setAccessible(true); //设置些属性是可以访问的
                String mtdName = "set"+ f.getName().substring(0,1).toUpperCase() + f.getName().substring(1);
                //直接 T.getMethod 当没有Method时会抛出异常。例如类里面有 serialVersionUID 时，或者set方法有private修饰时
                Class cs= f.getType();

                if(mapParam.get(f.getName()) != null){
                    //直接 T.getMethod 当没有Method时会抛出异常。例如类里面有 serialVersionUID 时，或者set方法有private修饰时 。如何解决？
                    //第一种方案：将mapParam放入上面
//                    Method method = T.getMethod(mtdName, f.getType());
                    //第二种方案：获取所有方法进行比对
                    Method[] methods = T.getMethods();
                    for(Method method : methods){
                        if(mtdName.equals(method.getName())){
                            try{

                                if(!Modifier.isPublic(method.getModifiers())){
                                    method.setAccessible(true);
                                }

                                method.invoke(obj,mapParam.get(f.getName()));
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return (S) obj;
    }

    public static List<Object> getAllFiledList(Class T){
        Field[] fs = T.getDeclaredFields();
        ArrayList fileList = new ArrayList(Arrays.asList(fs));
        Class tempClass = T;
        while (tempClass != null){
            Class superClass = tempClass.getSuperclass();
            if(superClass != null){
                Field[] declaredFields = superClass.getDeclaredFields();
                ArrayList fList = new ArrayList(Arrays.asList(declaredFields));
                fileList.addAll(fList);
            }
            tempClass = superClass;
        }
        return fileList;
    }
}
