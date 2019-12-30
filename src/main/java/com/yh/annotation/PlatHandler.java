package com.yh.annotation;

import java.lang.annotation.*;

/**
 * @Author: yinhui
 * @Date: 2019/11/18 10:46
 * @Version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
// 生成DOC文件,就是 HTML文件
@Documented
@Inherited
public @interface PlatHandler {

    String value();
}
