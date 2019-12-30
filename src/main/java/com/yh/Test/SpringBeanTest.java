package com.yh.Test;

import com.yh.bean.StudentBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试生成bean的一生
 * @Author: yinhui
 * @Date: 2019/6/27 16:52
 * @Version 1.0
 */
public class SpringBeanTest {

    public static void main(String[] str) throws Exception {
        //1、读取配置文件实例化一个IoC容器
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        StudentBean studentBean = (StudentBean) context.getBean("student");
        //2、从容器中获取Bean
        StudentBean studentBean = context.getBean("student",StudentBean.class);
        StudentBean studentBean2 = new StudentBean();
        //3、执行业务逻辑
        studentBean.play();
        System.out.println(studentBean);
        //关闭容器
        ((ClassPathXmlApplicationContext) context).close();
    }
}
