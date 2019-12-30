/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.yh.annotation;

import java.lang.annotation.*;

/**
 * 合法签名
 * @author yinhui
 * @version SecureSign, v0.1 2018/10/19 10:04
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
// 生成DOC文件,就是 HTML文件
@Documented
//允许子类继承父类的注解。
@Inherited
public @interface SecureSign {

    /**
     *  是否需要验证签名
     */
    boolean  required() default true;

//    int[] value();

    /**
     * 接口调用类型
     */
    RequestTypeEnum type() default RequestTypeEnum.INTERFACE;
}
