package com.yh.Test;

import aj.org.objectweb.asm.ClassVisitor;

/**
 * @Author: yinhui
 * @Date: 2019/8/26 14:55
 * @Version 1.0
 */
public class AddField extends ClassVisitor {
    public AddField(int i) {
        super(i);
    }
}
