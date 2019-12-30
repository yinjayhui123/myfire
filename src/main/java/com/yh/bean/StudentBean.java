package com.yh.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.stereotype.Component;

/**
 * 一个学生类(Bean)，能体现其生命周期的Bean
 * @Author: yinhui
 * @Date: 2019/6/27 16:47
 * @Version 1.0
 */
public class StudentBean implements BeanNameAware, BeanFactoryAware,InitializingBean , DisposableBean  {
    private String name;

    public StudentBean(){
        System.out.println("实例化Student");
    }

    /**
     * 设置对象属性
     */
    public void setName(String name){
        System.out.println("设置对象属性setName().."+name);
        this.name = name;
    }

    //Bean的初始化方法
    public void initStudentBean(){
        System.out.println("StudentBean这个Bean：初始化");
    }

    //Bean的初始化方法 InitializingBean 接口
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("StudentBean这个Bean2：初始化");
    }

    //Bean的销毁方法
    public void destroyStudentBean(){
        System.out.println("StudentBean这个Bean：销毁");
    }

    //Bean的销毁方法 DisposableBean 接口
    @Override
    public void destroy() throws Exception {
        System.out.println("StudentBean这个Bean2：销毁");
    }

    //Bean的使用
    public void play() {
        System.out.println("StudentBean这个Bean：使用");
    }

    /* 重写toString
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Student [name = " + name + "]";
    }

    //调用BeanNameAware的setBeanName()
    //传递Bean的ID。
    @Override
    public void setBeanName(String name) {
        System.out.println("调用BeanNameAware的setBeanName()..." +name );
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

        System.out.println("调用BeanFactoryAware的setBeanFactory()..."+beanFactory.toString());
    }

//    @Override
//    public Object getObject() throws Exception {
//        return null;
//    }
//
//    @Override
//    public Class<?> getObjectType() {
//        return null;
//    }
//
//    @Override
//    public boolean isSingleton() {
//        return false;
//    }
}
