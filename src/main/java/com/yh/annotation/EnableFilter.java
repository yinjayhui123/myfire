package com.yh.annotation;

import java.lang.annotation.*;

/**
 * 是否开启活动的过滤器
 * @Author: yinhui
 * @Date: 2020/1/15 16:25
 * @Version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface EnableFilter {

    String value() default "";
}
