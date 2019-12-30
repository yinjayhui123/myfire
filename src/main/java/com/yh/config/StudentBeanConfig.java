package com.yh.config;

import com.yh.bean.StudentBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: yinhui
 * @Date: 2019/8/5 11:33
 * @Version 1.0
 */
@Configuration
public class StudentBeanConfig {

    @Bean
    public StudentBean sebbbBean(){
        return new StudentBean();
    }
}
