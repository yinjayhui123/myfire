package com.yh.annotation;

import java.lang.annotation.*;

/**
 * 请求限制
 * @Author: yinhui
 * @Date: 2019/12/30 10:13
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RequestLimit {

    /**
     * 多长时间不允许重复，默认三秒
     */
    int seconds() default 3;
}
